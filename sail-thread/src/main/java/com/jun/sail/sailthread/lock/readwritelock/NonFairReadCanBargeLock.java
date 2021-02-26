package com.jun.sail.sailthread.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示非公平读写锁，读锁在队列头结点不是写锁时是可以插队的
 * @author Jun
 * 创建时间： 2020/4/19
 */
public class NonFairReadCanBargeLock {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
        new Thread(()->write(), "线程1").start();
        new Thread(()->read(), "线程2").start();
        new Thread(()->read(), "线程3").start();

        new Thread(()->write(), "线程4").start();
        new Thread(()->read(), "线程5").start();

        new Thread(()->{
            for (int i = 0; i < 1000; i++) {
               new Thread(()->read(), "子线程" + i).start();
            }
        }, "线程6").start();
    }

    /**
     * 查看电影票
     */
    private static void read(){
        //System.out.println(Thread.currentThread().getName() + "开始尝试获取读锁");
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在查看座位。。。");
            Thread.sleep(20);
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
        //System.out.println(Thread.currentThread().getName() + "开始尝试获取写锁");
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在预订座位。。。");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "预订座位成功，释放了写锁");
            writeLock.unlock();
        }
    }
}



