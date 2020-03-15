package com.jun.sail.sailthread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jun
 * 创建时间： 2020/3/8
 */
public class Task2SingleThreadExecutor {

    //利用并发包里的Excutors的newSingleThreadExecutor产生一个单线程的线程池，
    // 而这个线程池的底层原理就是设置核心线程和最大线程都是1
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        Thread thread1 = new Thread(()->{
            System.out.println("a");
        });

        Thread thread2 = new Thread(()->{
            System.out.println("b");
        });

        Thread thread3 = new Thread(()->{
            System.out.println("c");
        });

        for (int i = 0; i < 2; i++) {
            executorService.submit(thread1);
            executorService.submit(thread2);
            executorService.submit(thread3);
        }

        //作用是关闭线程池，终止线程执行
        executorService.shutdown();
    }
}
