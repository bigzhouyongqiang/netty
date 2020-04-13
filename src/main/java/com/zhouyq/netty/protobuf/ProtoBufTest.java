package com.zhouyq.netty.protobuf;

/**
 * @Description
 *      Google protocol buffer 学习及实践
 *      1. 下载protocol 编译器.proto文件生成java文件的命令
 *      2. 编写.proto文件
 *      3. 使用编译器生成java文件  protoc --java_out=src/main/java/ src/main/java/com/zhouyq/netty/protobuf/Student.proto
 *
 *      【注意】： 不要修改protoc生成的java文件
 *
 * @Date 2020/4/13 22:57
 * @Author zhouyq
 */
public class ProtoBufTest {
    public static void main(String[] args) throws Exception{

        // 调用protoc 生成的java类生成Student对象，此处使用的是Builder模式生成的对象
        DataInfo.Student student = DataInfo.Student.newBuilder().
                setName("zhouyq").setAge(24).setAddress("西安").build();

        // 对象序列化（编码）成字节数组
        byte[] student2ByteArray = student.toByteArray();

        // 字节数组反序列化（解码）成对象
        DataInfo.Student student1 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());
    }
}
