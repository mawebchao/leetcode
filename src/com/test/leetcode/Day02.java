package com.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day02 {
    public static void main(String[] args) {
        //给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String newline = null;
        System.out.print("请输入想要测试的字符串：");
        try {
            while (((newline = br.readLine()) != null)) {
                String newStr=process(newline);
                System.out.println("处理后的字符串："+newStr);
                System.out.print("请输入想要测试的字符串：");
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

    private static String process(String newline) {
        char[] chars=newline.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]>='A'&&chars[i]<='Z'){
                chars[i]= (char) (chars[i]+32);
            }
        }
        return new String(chars);
    }
}
