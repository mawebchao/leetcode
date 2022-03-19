package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器端已经启动....");
        // 1.创建服务器端通道
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        // 2.切换异步非阻塞
        sChannel.configureBlocking(false);
        // 3.绑定连接
        sChannel.bind(new InetSocketAddress(8080));
        // 4.获取选择器
        Selector selector = Selector.open();
        // 5.将通道注册到选择器 "并且指定监听接受事件"
        sChannel.register(selector, SelectionKey.OP_ACCEPT);

        //打印一下现在selector的select
        int select=selector.select();
        System.out.println("第一次连接上后的结果：(当前就绪事件个数)"+select);

        // 6. 轮训式获取选择 "已经准备就绪"的事件
        while (select > 0) {

            // 7.获取当前选择器所有注册的"选择键(已经就绪的监听事件)"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            System.out.println("--------waiting event detected！！---------");
            System.out.println("当前已经就绪的监听事件个数为："+selector.selectedKeys().size());
            while (it.hasNext()) {

                // 8.获取准备就绪的事件
                SelectionKey sk = it.next();

                // 9.判断事件准备就绪
                if (sk.isAcceptable()) {
                    System.out.println("有准备就绪的等待连接的事件");
                    // 10.若"接受就绪",获取客户端连接
                    SocketChannel socketChannel = sChannel.accept();
                    // 11.设置阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12.将该通道注册到服务器上
                    socketChannel.register(selector, SelectionKey.OP_READ);

                } else if (sk.isReadable()) {
                    System.out.println("有准备就绪的等待读取的事件");
                    // 13.获取当前选择器"就绪" 状态的通道
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    // 14.读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                it.remove();
            }

            select=selector.select();
            System.out.println("第一次遍历结果：(当前就绪事件个数)"+select);
        }
    }
}
