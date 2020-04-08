package com.zhouyq.netty.example01.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description TODO
 * @Date 2020/4/8 23:56
 * @Author zhouyq
 */
public class StrServerMain {
    private int port;

    public StrServerMain(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception{
        new StrServerMain(8889).start();
    }

    private void start() throws Exception{
        NioEventLoopGroup eventExecutors = null;
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            eventExecutors = new NioEventLoopGroup();
            serverBootstrap.group(eventExecutors)
                    .channel(NioServerSocketChannel.class)
                    .localAddress("localhost", port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast("StrServerHandler", new StrServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            System.out.println("Server -> 服务启动成功，监听端口：" + port);

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            eventExecutors.shutdownGracefully().sync();
        }


    }
}
