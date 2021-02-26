package com.jun.sail.sailthread.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示非公平读写锁，读锁在队列头结点是写锁时不能插队
 * @author Jun
 * 创建时间： 2020/4/19
 */
public class NonFairReadNotBargeLock {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
        new Thread(()->write(), "线程0").start();
        new Thread(()->read(), "线程1").start();
        new Thread(()->read(), "线程2").start();

        new Thread(()->write(), "线程3").start();

        //因为线程3是写并且在队列中头结点中，所以线程4只能排队
        new Thread(()->read(), "线程4").start();
    }

    /**
     * 查看电影票
     */
    private static void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在查看座位。。。");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }


    /**
     *预订电影票座位
     */
    private static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在预订座位。。。");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "预订座位成功，释放了写锁");
            writeLock.unlock();
        }
    }
}



