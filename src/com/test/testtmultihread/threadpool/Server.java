package com.test.testtmultihread.threadpool;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("socket tcp服务器端启动....");
        ServerSocket serverSocket = new ServerSocket(8080);
        BlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(2, 2, 1, TimeUnit.SECONDS, queue);
        executorService.allowCoreThreadTimeOut(true);
        Logger log=Logger.getLogger("aa");
        // 等待客户端请求
        try {
            while (true) {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();


                executorService.execute(() -> {
                    log.info("new connection created!!!"+Thread.currentThread().getName());
                    String str=null;

                    while(str==null||!str.equals("bye")){
                        try {
                            System.out.println("waiting for input:");
                            // 转换成string类型
                            byte[] buf = new byte[1024];
                            int len = inputStream.read(buf);
                            str = new String(buf, 0, len);
                            System.out.print("服务器接受客户端内容:" + str);
                        } catch (Exception e) {
                            // TODO: handle exception
//                            System.out.println(e);
                            break;
                        }
                    }
                    log.info("connection exit!!! connection name:"+Thread.currentThread().getName());
                });
                log.info("having active thread "+((ThreadPoolExecutor)executorService).getActiveCount()+" in pool currently!!!");


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }
}
