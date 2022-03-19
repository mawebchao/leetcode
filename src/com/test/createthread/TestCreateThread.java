package com.test.createthread;

import java.util.concurrent.*;

class MyThread1 implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("线程"+Thread.currentThread().getName()+"启动了");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        System.out.println("线程"+Thread.currentThread().getName()+"启动了");
    }
}



public class TestCreateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //使用继承方式创建thread
        Thread t1=new Thread(new MyThread1(),"mythread1");
        t1.start();
        //使用实现runnable接口的方式创建线程
        Thread t2=new Thread(new MyThread2(),"mythread2");
        t2.start();
        //匿名内部类方式创建线程
        Thread t3=new Thread(()->{
            System.out.println("线程"+Thread.currentThread().getName()+"启动了");
        },"mythread3");
        t3.start();
        //使用futuretask创建线程，并获得执行结果
        FutureTask task=new FutureTask<String>(()->{
            try {
                Thread.sleep(10000);
                return "fuck you";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
        FutureTask task1=new FutureTask<String>(()->{
            try {
                Thread.sleep(10000);
                return "fuck you";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
        Thread t4=new Thread(task,"mythread4");
        t4.start();
        Object o = task.get();
        System.out.println("mythread4执行结果为："+o);
//        Thread t5=new Thread(task1,"mythread5");
//        t5.start();
//        Object o2 = task1.get();
//        System.out.println("mythread5执行结果为："+o2);

        //使用线程池启动多线程
        ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(2, 4, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });
        poolExecutor.submit(task);
        Object o1 = task.get();
        System.out.println("线程池执行task的结果为："+o1);
        poolExecutor.shutdown();
    }
}
