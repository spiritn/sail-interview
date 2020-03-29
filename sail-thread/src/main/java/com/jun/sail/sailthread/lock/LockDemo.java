package com.jun.sail.sailthread.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jun
 * 创建时间： 2020/3/23
 */
public class LockDemo {

    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        lockInterrunpter();

    }


    public static void lockInterrunpter(){
        Thread thread = new Thread(() -> {
            try {
                //lockInterruptibly在等待获取锁期间，如果被中断，会抛出InterruptedException异常
                lock1.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("被打断了");
            } finally {
               lock1.unlock();
            }
        });

        lock1.lock();

        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);

            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






}
