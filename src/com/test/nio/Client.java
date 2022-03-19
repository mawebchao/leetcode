package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端已经启动....");
        // 1.创建网络通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        // 2.切换为异步非阻塞
        sChannel.configureBlocking(false);
        // 3.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入你想说的话：");
        while (scanner.hasNext()) {
            String str = scanner.next();
            byteBuffer.put((new Date().toString() + "\n" + str).getBytes());
            // 4.切换读取模式
            byteBuffer.flip();
            sChannel.write(byteBuffer);
            byteBuffer.clear();
            System.out.print("输入你想说的话：");
        }
        //5.关闭通道
        sChannel.close();
    }
}
