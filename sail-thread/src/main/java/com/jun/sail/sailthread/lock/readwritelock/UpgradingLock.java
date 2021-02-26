package com.jun.sail.sailthread.lock.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示写锁降级 ，读锁升级为写锁时不行的
 * @author Jun
 * 创建时间： 2020/4/19
 */
public class UpgradingLock {

    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) throws InterruptedException {
        //写锁降级为读锁是可以的
        Thread thread1 = new Thread(() -> writeDownGrading(), "线程2");
        thread1.start();


        //保证线程1已经执行完
        thread1.join();
        //读锁升级为写锁是不行的
        new Thread(()->readUpgrading(), "线程1").start();

    }

    /**
     * 查看电影票
     * 读锁升级 （不行）
     */
    private static void readUpgrading(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在查看座位。。。");
            Thread.sleep(1000);
            System.out.println("尝试来升级，获取写锁");
            writeLock.lock();
            System.out.println("成功获取写锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }


    /**
     * 预订电影票座位
     * 写降级
     */
    private static void writeDownGrading(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了写锁，正在预订座位。。。");
            Thread.sleep(2000);
            System.out.println("尝试来降级，获取读锁");
            readLock.lock();
            System.out.println("成功获取读锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName() + "预订座位成功，释放了写锁");
        }
    }
}



