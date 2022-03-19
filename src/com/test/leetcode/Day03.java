package com.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 {
    public static void main(String[] args) {
//给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String newline = null;
        List<String> strings = new ArrayList<>();
        System.out.print("请输入想要测试的字符串：");
        try {
            while ((!(newline = br.readLine()).equals("exit"))) {
                strings.add(newline);
                System.out.print("请输入想要测试的字符串：");
            }
            process(strings);
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

    private static void process(List<String> newline) {
        String[] map = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        List<String> result = new ArrayList<>();
        Map<String, String> mersi = new HashMap<>();
        StringBuilder newResult = new StringBuilder();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < newline.size(); i++) {
            char[] chars = newline.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                System.out.println(chars[j] - 97);
                newResult.append(map[chars[j] - 97]);
            }
            if (!result.contains(newResult.toString()))
                result.add(newResult.toString());
            mersi.put(newline.get(i),newResult.toString() );
            newResult.delete(0, newResult.length());
        }
        output.append("输入：words=" + newline + "\n");
        output.append("输出：" + result.size() + "\n解释：\n各单词翻译如下：\n");
        for (Map.Entry<String, String> entry:
        mersi.entrySet()){
            output.append("\""+entry.getKey()+"\"-> \""+entry.getValue()+"\"\n");
        }

        System.out.println(output.toString());
    }
}
