package com.jun.sail.sailthread.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jun
 * 创建时间： 2020/3/8
 */
public class Task2Lock {

    static Lock lock = new ReentrantLock();
    static int count = 0;
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();
    static Condition condition3 = lock.newCondition();

    /**
     * 下面的while只是为了控制打印的次数
     */
    public static void main(String[] args)  {
        Thread tA = new Thread(() -> {
            while (count < 4) {
                //先获取锁
                lock.lock();
                //如果count取余3不等于0，就await
                if (count % 3 != 0) {
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //否则就执行任务并count++,唤醒线程2
                System.out.println("a");
                count++;
                condition2.signal();
                lock.unlock();
            }
        });

        Thread tB = new Thread(() -> {
            while (count < 4) {
                lock.lock();
                if (count % 3 != 1) {
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("l");
                count++;
                condition3.signal();
                lock.unlock();
            }
        });

        Thread tC = new Thread(() -> {
            while (count < 4) {
                lock.lock();
                if (count % 3 != 2) {
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("i");
                count++;
                condition1.signal();
                lock.unlock();
            }
        });
        tA.start();
        tB.start();
        tC.start();
    }
}
