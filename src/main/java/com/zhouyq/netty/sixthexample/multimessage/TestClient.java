package com.zhouyq.netty.sixthexample.multimessage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Description TODO
 * @Date 2020/4/19 12:01
 * @Author zhouyq
 */
public class TestClient {

    public static void main(String[] args) throws Exception{
        EventLoopGroup eventGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new TestClentInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventGroup.shutdownGracefully();
        }

    }
}
