package com.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Day07 {
    public static void main(String[] args) throws IOException {
        //最短补全词
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入licensePlate ：");
        String licensePlate = br.readLine();
        System.out.print("请输入words ：");
        String str = null;
        List<String> words = new ArrayList<>();
        try {
            while (!"exit".equals(str = br.readLine())) {
                words.add(str);
            }
            System.out.println("输入：licensePlate = \"" + licensePlate + "\", words = " + Arrays.toString(words.toArray()));
            System.out.println("输出：\"" + process(licensePlate, words) + "\"");
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

    private static String process(String licensePlate, List<String> words) {
        String result = null;
        int minLength = Integer.MAX_VALUE;
        for (String word : words
        ) {
            if (isMatched(licensePlate, word) && minLength > word.length()) {
                minLength = word.length();
                result = word;
            }
        }
        return result;
    }

    private static boolean isMatched(String licensePlate, String word) {
        Map<Character,Integer> licenceMAp=treansferStringToMap(licensePlate);
        Map<Character,Integer> nowWordMAp=treansferStringToMap(word);
        for (Character key:licenceMAp.keySet()
             ) {
            if(!nowWordMAp.containsKey(key)||nowWordMAp.get(key)<licenceMAp.get(key))
                return false;
        }
        return true;
    }

    private static Map<Character, Integer> treansferStringToMap(String licensePlate) {
        Map<Character,Integer> result=new HashMap<>();
        for (char c:licensePlate.toLowerCase(Locale.ROOT).toCharArray()
             ) {
            if(c>='A'&&c<='z'){
                if(result.containsKey(c)){
                    result.put(Character.valueOf(c),result.get(Character.valueOf(c))+1);
                }else{
                    result.put(Character.valueOf(c),1);
                }
            }

        }
        return result;
    }
}
