package com.zhouyq.netty.sixthexample.multimessage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Description TODO
 * @Date 2020/4/19 12:09
 * @Author zhouyq
 */
public class TestClentHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;

        // 根据不同类型给服务端发送不同的对象
        if (0 == randInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.PerSonType).
                    setPerson(MyDataInfo.Person.newBuilder().
                            setName("zhouyq").setAge(23).setAddress("西安").build()).
                    build();
        } else if (1 == randInt) {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.DogType)
                    .setDog(MyDataInfo.Dog.newBuilder()
                            .setName("小狗").setAge(10).setAddress("西安").build()).build();
        } else {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.CatType)
                    .setCat(MyDataInfo.Cat.newBuilder()
                            .setName("小喵").setAge(10).setAddress("西安").build()).build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
