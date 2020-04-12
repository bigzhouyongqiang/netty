package com.zhouyq.netty.secondexample.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @Description TODO
 * @Date 2020/4/9 0:03
 * @Author zhouyq
 */
public class StrServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Server -> 接到数据，开始处理。。。");

        /**
         *  反序列化
         */
        byte[] req = new byte[msg.readableBytes()];
        msg.readBytes(req);
        String requestBodyStr = new String(req, "UTF-8");

        System.out.println("Server -> 开始回写数据");
        String currentTime = LocalDateTime.now().toString();

        ByteBuf copiedBuffer = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.writeAndFlush(copiedBuffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常
        ctx.close();
        cause.printStackTrace();
    }
}
