package com.zhouyq.netty.sixthexample.multimessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description TODO
 * @Date 2020/4/19 11:09
 * @Author zhouyq
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        switch (msg.getDataType()){
            case PerSonType:
                System.out.println(msg.getPerson().getName());
                System.out.println(msg.getPerson().getAge());
                System.out.println(msg.getPerson().getAddress());
                break;
            case DogType:
                System.out.println(msg.getDog().getName());
                System.out.println(msg.getDog().getAge());
                System.out.println(msg.getDog().getAddress());
                break;
            case CatType:
                System.out.println(msg.getCat().getName());
                System.out.println(msg.getCat().getAge());
                System.out.println(msg.getCat().getAddress());
                break;
        }
    }
}
