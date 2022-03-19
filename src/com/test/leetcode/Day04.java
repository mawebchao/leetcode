package com.test.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day04 {
    static class Robot implements Cloneable {
        int x;
        int y;

        Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return x == robot.x && y == robot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "Robot{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {

        //机器人能否返回原点
        Robot robot = new Robot(0, 0);
        Robot robot1 = (Robot) robot.clone();
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(System.in));
        String newline = null;
        System.out.print("请输入想要测试的字符串：");
        try {
            do {
                newline = br.readLine();
                robot = move(robot, newline);
            } while (false);
            if (robot.equals(robot1))
                System.out.println("没有移动");
            else {
                System.out.println("移动了");
                System.out.println(robot);
                System.out.println(robot1);
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

    private static Robot move(Robot robot, String newline) {
        char[] chars = newline.toCharArray();
        for (char c : chars
        ) {
            decodeAndMove(robot, c);
        }
        return robot;
    }

    private static void decodeAndMove(Robot robot, char c) {
        switch (c) {
            case 'U':
                robot.y++;
                break;
            case 'D':
                robot.y--;
                break;

            case 'L':
                robot.x--;
                break;

            case 'R':

                robot.x++;
                break;

        }
    }
}
