package com.zhouyq.netty.secondexample.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @Description TODO
 * @Date 2020/4/8 18:53
 * @Author zhouyq
 */
public class StrClientMain {

    private String host;
    private int port;

    public StrClientMain(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws  Exception{
        new StrClientMain("127.0.0.1", 8889).start();
    }

    private void start() throws Exception{

        NioEventLoopGroup nioEventLoopGroup = null;
        try {
            // 客户端引导类
            Bootstrap bootstrap = new Bootstrap();

            // 构造bootstrap 对象

            // 可以理解成一个线程池，用这个线程池处理连接和处理数据
            nioEventLoopGroup = new NioEventLoopGroup();

            bootstrap.group(nioEventLoopGroup) // 多线程的处理（注册一个组）
                    .channel(NioSocketChannel.class) // 指定通道类型
                    .remoteAddress(new InetSocketAddress(host, port)) // 注册远程服务器地址
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new StrClienthandler());
                        }
                    });

            // 等待连接成功，否则一直阻塞
            ChannelFuture channelFuture = bootstrap.connect().sync();

            // 接受数据之后阻塞
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            nioEventLoopGroup.shutdownGracefully().sync();
        }
    }
}
