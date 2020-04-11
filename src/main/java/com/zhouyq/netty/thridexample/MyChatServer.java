package com.zhouyq.netty.thridexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description
 *      1. 当某个客户端上线后通知其他客户端此客户端上线
 *      2. 类似与群聊功能
 *          - 客户端发送消息
 *              - 其他客户端看到的是
 *                  - 【远程地址+端口 发送的消息】：  消息内容
 *              - 自己看到的是
 *                  -【自己】：消息内容
 * @Date 2020/4/11 21:08
 * @Author zhouyq
 */
public class MyChatServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
