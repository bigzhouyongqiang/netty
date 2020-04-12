package com.zhouyq.netty.secondexample.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description TODO
 * @Date 2020/4/8 19:29
 * @Author zhouyq
 */
public class StrClienthandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端接到服务器后调用
        System.out.println("client -> 开始连接服务器发送数据。。。。");
        byte[] bytes = "get current time".getBytes();

        /**
         * 序列化数据
         */
        ByteBuf buffer = Unpooled.buffer(bytes.length);
        // 往数组中写入数据
        buffer.writeBytes(bytes);

        ctx.writeAndFlush(buffer);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // 从服务器端接到数据之后调用
        System.out.println("client -> 读取服务器返回数据");

        /**
         * 反序列化数据
         */
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String msgBody = new String(bytes, "UTF-8");

        System.out.println("client -> "+ msgBody);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 发生异常是被调用

        ctx.close();
    }
}
