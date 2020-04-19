package com.zhouyq.netty.sixthexample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @Description TODO
 * @Date 2020/4/19 12:09
 * @Author zhouyq
 */
public class TestClentHandler extends SimpleChannelInboundHandler<MyDataInfo.Person> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Person msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        MyDataInfo.Person person = MyDataInfo.Person.newBuilder()
                .setName("zhouyq（姓名）").setAge(23).setAddress("西安").build();

        ctx.channel().writeAndFlush(person);
    }
}
