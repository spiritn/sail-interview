package com.jun.sail.sailthread.thread;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 是一个计数信号量，必须由获取它的线程释放
 * @author Jun
 * 创建时间： 2020/3/8
 */
public class Task2Semaphore {

    static Semaphore semaphore1 = new Semaphore(1);
    static Semaphore semaphore2 = new Semaphore(1);
    static Semaphore semaphore3 = new Semaphore(1);
    static  int count = 0;

    public static void main(String[] args) {

        Thread thread1 = new Thread(()->{
            while (true){
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //这里判断一次就可以控制执行的次数
                if(count < 4){
                    System.out.println("a");
                    count++;
                    semaphore2.release();
                }
            }
        });

        Thread thread2 = new Thread(()->{
            while (true){
                try {
                    //acquire方法会一直阻塞直到另一个线程释放他
                    semaphore2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b");
                count++;
                semaphore3.release();
            }
        });

        Thread thread3 = new Thread(()->{
            while (true){
                try {
                    semaphore3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("c");
                count++;
                semaphore1.release();
            }
        });

        try {
            //这里必须先获取semaphore2，semaphore3，阻止线程2,3运行。也就是必须有线程1先执行，并释放2的资源
            semaphore2.acquire();
            semaphore3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
        thread1.start();
        thread3.start();
    }
}
