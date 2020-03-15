package com.jun.sail.sailthread.thread;

/**
 * @author Jun
 * 创建时间： 2020/3/8
 */
public class TaskJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("a");
        });

        Thread thread2 = new Thread(()->{
            System.out.println("b");
        });

        Thread thread3 = new Thread(()->{
            System.out.println("c");
        });

        //join方法会使得调用线程等待直到thread1结束die
        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
    }
}
