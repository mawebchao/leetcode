package com.test.nio2;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(String.valueOf(Client.class));

    @Test
    public void test1() throws IOException {
//        test("22+33+44+55");
        testBIOClient("22+33+44+55");
    }
    private void testBIOClient(String exp) throws IOException {
        logger.info("开始建立连接");
        long timeMillis = System.currentTimeMillis();
        Socket socket =new Socket("localhost",8080);
        logger.info("连接建立成功，耗时{}毫秒"+(System.currentTimeMillis()-timeMillis));
        OutputStream outputStream = socket.getOutputStream();
        logger.info("传入计算表达式：{}"+exp);
        outputStream.write(exp.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        InputStream inputStream = socket.getInputStream();
        int read;
        byte[] bytes = new byte[1024];
        StringBuilder builder = new StringBuilder();
        while((read=inputStream.read(bytes))!=-1){
            builder.append(new String(bytes,0,read));
        }
        logger.info("服务器返回结果为：{}"+ builder);
        inputStream.close();
        outputStream.close();
        socket.close();
    }
    private void test(String exp) throws IOException {
        logger.info("开始建立连接");
        long timeMillis = System.currentTimeMillis();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress(8080));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isConnectable()) {
                    logger.info("连接建立成功，耗时{}毫秒"+(System.currentTimeMillis()-timeMillis));
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.configureBlocking(false);
                    channel.finishConnect();
                    channel.register(selector, SelectionKey.OP_WRITE);
                }else if(selectionKey.isWritable()){
                    logger.info("传入计算表达式：{}"+exp);
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    byteBuffer.put(exp.getBytes(StandardCharsets.UTF_8));
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    byteBuffer.clear();
                    channel.register(selector, SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    int read = channel.read(byteBuffer);
                    byteBuffer.flip();
                    StringBuilder builder = new StringBuilder();
                    builder.append(StandardCharsets.UTF_8.decode(byteBuffer));
                    logger.info("服务器返回结果为：{}"+ builder);
                    channel.close();
                    return;
                }
            }
        }
    }
}

