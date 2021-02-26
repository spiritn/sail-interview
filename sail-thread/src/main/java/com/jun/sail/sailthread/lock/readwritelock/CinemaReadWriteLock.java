package com.jun.sail.sailthread.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁
 * @author Jun
 * 创建时间： 2020/4/19
 */
public class CinemaReadWriteLock {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
        //可以一起读
        new Thread(()->read(), "线程1").start();
        new Thread(()->read(), "线程2").start();

        //但是不能一起写
        new Thread(()->write(), "线程3").start();
        new Thread(()->write(), "线程4").start();
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



