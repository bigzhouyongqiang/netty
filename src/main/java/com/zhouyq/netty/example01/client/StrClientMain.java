package com.zhouyq.netty.example01.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import sun.security.pkcs.PKCS7;

import java.net.InetSocketAddress;
import java.util.Base64;

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

    public static void main(String[] args) {
        new StrClientMain("localhost", 888).start();
    }

    private void start() {

        // 客户端引导类
        Bootstrap bootstrap = new Bootstrap();

        // 构造bootstrap 对象

        // 可以理解成一个线程池，用这个线程池处理连接和处理数据
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        bootstrap.group(nioEventLoopGroup) // 多线程的处理（注册一个组）
                .channel(NioSocketChannel.class) // 指定通道类型
                .remoteAddress(new InetSocketAddress(host, port)) // 注册远程服务器地址
                .handler(new ChannelInitializer<SocketChannel>() {
                    
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StrClienthandler());
                    }
                });
    }
}
