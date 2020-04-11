package com.zhouyq.netty.thridexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.sql.SQLOutput;

/**
 * @Description
 * @Date 2020/4/11 21:29
 * @Author zhouyq
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch->{
            if (channel != ch) {
                /**
                 * bugfix:
                 * 由于netty的writeAndFlush方法没有类似java的 println方法，
                 * println方法可以给输出添加一个换行符（\n）,而writeAndFlush方法没有换行符号
                 * 导致客户端接收到消息后无法显示到控制台，需要客户端敲一下键盘的回车键才能显示出来，
                 * 所以此处需要添加换行符号 "\n"
                 */
                ch.writeAndFlush( "【" + ch.remoteAddress() + "发送的消息】：" + msg + "\n");
            } else {
                ch.writeAndFlush( "【自己】：" + msg + "\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】-"+channel.remoteAddress()+" 加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //channelGroup.remove(channel);  netty 会自动调用
        channelGroup.writeAndFlush("【服务器】-" + channel.remoteAddress() + " 离开\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+" 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
