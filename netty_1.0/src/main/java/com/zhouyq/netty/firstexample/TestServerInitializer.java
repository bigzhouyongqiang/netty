package com.zhouyq.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Description TODO
 * @Date 2020/4/7 23:22
 * @Author zhouyq
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("HttpServerCodec",new HttpServerCodec());
        pipeline.addLast("TsetHttpServerHandler", new TestHttpServerHandler());
    }
}
