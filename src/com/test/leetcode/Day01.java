package com.test.leetcode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

public class Day01 {
//    🌲原题样例：较大分组的位置
//    在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
//
//    例如，在字符串 s = “abbxxxxzyy” 中，就含有 “a”, “bb”, “xxxx”, “z” 和 “yy” 这样的一些分组。
//
//    分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 “xxxx” 分组用区间表示为 [3,6] 。
//
//    我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
//
//    找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。

    public static void main(String[] args) {
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String newline = null;
        System.out.print("请输入想要测试的字符串：");
        try {
            while (((newline = br.readLine()) != null)) {
                long start = System.currentTimeMillis();
                process(newline);
                System.out.println("耗时"+(System.currentTimeMillis()-start)+"毫秒");
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

    private static void process(String str) {
        char[] bytes = str.toCharArray();
        int count = 0;
        char c = 0;
        int from = 0;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == c) {
                count++;
            } else {
                if (count >= 3) {
                    sb.append("[" + from + "," + (i - 1) + "]");
                }
                count = 1;
                c = bytes[i];
                from = i;
            }

        }
        System.out.println(sb.toString());

    }

}
