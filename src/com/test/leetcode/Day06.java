package com.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 {
    public static void main(String[] args) {
        //给定一个二进制数组， 计算其中最大连续 1 的个数。
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String newline = null;
        System.out.print("请输入想要测试的字符串：");
        try {
            while ((!(newline = br.readLine()).equals("exit"))) {
                int coutnt = countMaxInRow(newline);
                System.out.println("输入："+ new ArrayList<>(Arrays.asList(newline.split(""))));
                System.out.print("最大的连续1的个数是：" + coutnt);
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int countMaxInRow(String newline) {
        int maxCount = 0;
        int count=0;
//        char x = (char) -1;
        for (char c : newline.toCharArray()
        ) {
//            if (x == -1 ) {
//                x=c;
//            }
            if(c=='1'){
                count++;
            }else{
                if(maxCount<count){
                    maxCount=count;
                }
                count=0;
            }
        }
        return maxCount;

    }
}
