package com.jun.sail.sailthread.lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个Lock里面可以创建多个Condition实例，实现多路通知
 *
 * notify()方法进行通知时，被通知的线程时Java虚拟机随机选择的，但是ReentrantLock结合Condition可以实现有选择性地通知
 */
public class WaitNotify {

    static ReentrantLock lock = new ReentrantLock();
    static Condition conditionA  = lock.newCondition();
    static Condition conditionB = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThreadA = new Thread(new WaitA(), "WaitThreadA");
        waitThreadA.start();
        Thread waitThreadB = new Thread(new WaitB(), "WaitThreadB");
        waitThreadB.start();
        TimeUnit.SECONDS.sleep(2);
        lock.lock();
        try {
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    static class WaitA implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " begin await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                conditionA.await();
                System.out.println(Thread.currentThread() + " end await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class WaitB implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread() + " begin await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                conditionB.await();
                System.out.println(Thread.currentThread() + " end await @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
