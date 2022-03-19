package com.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day05 {
    static class MyArrayList<T> extends ArrayList{

        public MyArrayList addAndRet(T o) {
            super.add(o);
            return this;
        }

        @Override
        public Object get(int index) {
            try{
                Object o=super.get(index);
                return o;
            }catch (IndexOutOfBoundsException e){
                return 0;
            }

        }
    }
    //给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
    //
    //在「杨辉三角」中，每个数是它左上方和右上方的数的和。
    public static void main(String[] args) {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String newline = null;
        System.out.print("请输入想要测试的字符串：");
        try {
            if ((newline = br.readLine()) != null) {
                int num = Integer.parseInt(newline);
                System.out.println("输入：num=" + num);
                List<List<Integer>> result = writeTriangle(num);
                System.out.println("输出："+result);
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

    private static List<List<Integer>> writeTriangle(int num) {
        MyArrayList result = new MyArrayList<>();
        List nums=new MyArrayList().addAndRet(1);
        for (int i = 0; i < num; i++) {
            nums=calRowNmus(i+1,nums);
            result.add(nums);
        }
        return result;
    }

    private static MyArrayList calRowNmus(int i, List nums) {
        MyArrayList ret=new MyArrayList();
        for (int j = 1; j <=i; j++) {
            if(j==1||j==i){
                ret.add(1);
            }else{
                ret.add(Integer.parseInt( nums.get(j-2)+"")+ Integer.parseInt(nums.get(j-1)+""));
            }
        }
        return ret;
    }
}
