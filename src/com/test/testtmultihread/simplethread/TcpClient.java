package com.test.testtmultihread.simplethread;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        System.out.println("socket tcp 客户端启动....");
//        Socket socket = new Socket("127.0.0.1", 8080);
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("我是盖世英雄".getBytes());
//        socket.close();


        // TODO 自动生成的方法存根

        String readline = null;
        String inTemp = null;
        //String outTemp = null;
        String turnLine = "\n";
        final String client = "Client:";
        final String server = "Server:";

        int port = 8080;
        byte ipAddressTemp[] = {127, 0, 0, 1};
        InetAddress ipAddress = InetAddress.getByAddress(ipAddressTemp);

        //首先直接创建socket,端口号1~1023为系统保存，一般设在1023之外
        Socket socket = new Socket(ipAddress, port);

        //创建三个流，系统输入流BufferedReader systemIn，socket输入流BufferedReader socketIn，socket输出流PrintWriter socketOut;
        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());

        while(readline != "bye"){

            System.out.println(client);
            readline = systemIn.readLine();
            //System.out.println(readline);

            socketOut.println(readline);
            socketOut.flush();    //赶快刷新使Server收到，也可以换成socketOut.println(readline, ture)

            //outTemp = readline;
//            inTemp = socketIn.readLine();

            //System.out.println(client + outTemp);
//            System.out.println(server + turnLine + inTemp);

        }

        systemIn.close();
        socketIn.close();
        socketOut.close();
        socket.close();
    }
}
