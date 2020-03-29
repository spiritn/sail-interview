package com.jun.sail.sailthread.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 什么是可重入锁
 *
 * @author Jun
 * 创建时间： 2020/3/29
 */
public class GetHolderCount {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock.getHoldCount());

        lock.lock();
        System.out.println(lock.getHoldCount());

        //上面拿到锁之后，不需要解锁这里可以再次拿到锁
        lock.lock();
        System.out.println(lock.getHoldCount());

        lock.lock();
        System.out.println(lock.getHoldCount());

        lock.unlock();
        System.out.println(lock.getHoldCount());

        lock.unlock();
        System.out.println(lock.getHoldCount());

        lock.unlock();
        System.out.println(lock.getHoldCount());
    }


}
