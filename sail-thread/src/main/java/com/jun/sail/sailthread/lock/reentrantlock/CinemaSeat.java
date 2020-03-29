package com.jun.sail.sailthread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示ReentrantLock，模拟预订电影票
 * @author Jun
 * 创建时间： 2020/3/29
 */
public class CinemaSeat {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(CinemaSeat::bookSeat,"线程1").start();
        new Thread(CinemaSeat::bookSeat,"线程2").start();
        new Thread(CinemaSeat::bookSeat,"线程3").start();
        new Thread(CinemaSeat::bookSeat,"线程4").start();
    }


    private static void bookSeat(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始预订票了");
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName() + " 预订票结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
