package com.test.fuckmsb.thread;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t=new Thread(r);
                        t.setName("abcdefg");
                        return t;
                    }
                },new ThreadPoolExecutor.AbortPolicy());
        executor.execute(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        });
//        System.out.println(Integer.SIZE - 3);
//        System.out.println("CAPACITY="+((1<<29)-1));
//        System.out.println("RUNNING="+Integer.toBinaryString(-1));
        executor.shutdownNow();
    }
}
