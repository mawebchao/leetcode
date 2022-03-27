package com.test.socket;

//import java.awt.desktop.ScreenSleepEvent;
import java.io.*;

public class TestIOInLinux {
    public static void main(String[] args) {
        BufferedWriter bw=null;
        BufferedReader br=null;
        String line;
        try {
//            bw=new BufferedWriter(new FileWriter("/opt/data/test.txt"));
            bw=new BufferedWriter(new FileWriter("d:/test.txt"));

            br=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("请输入想要写入的内容：");
            while(!(line=br.readLine()).equals("bye")){
                bw.write(line+"\n");
                System.out.print("请输入想要写入的内容：");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
