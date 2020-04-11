package com.zhouyq.netty.thridexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description TODO
 * @Date 2020/4/11 22:12
 * @Author zhouyq
 */
public class MychatClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        BufferedReader br = null;
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("127.0.0.1",8899).sync().channel();

            // 获取键盘输入信息，并发送到服务端，服务端会广播到其他客户端
            br = new BufferedReader(new InputStreamReader(System.in));
            String say = br.readLine();

            // 当客户端输入bye的时候将此客户端和其他服务器断开
            while (!"bye".equals(say) && null != say){
                channel.writeAndFlush(say+ System.lineSeparator());
                say = br.readLine();
            }

        } finally {
            eventLoopGroup.shutdownGracefully();
            br.close();
        }
    }
}
