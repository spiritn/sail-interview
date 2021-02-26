package com.jun.sail.sailthread.lock.spin;


import java.util.concurrent.atomic.AtomicReference;

/**
 * 来自己实现一个自旋锁
 * @author Jun
 * 创建时间： 2020/4/19
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();


    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁 ");
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到自旋锁");
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        };

        new Thread(runnable, "线程1").start();
        new Thread(runnable, "线程2").start();
    }


    private void lock(){
        Thread thread = java.lang.Thread.currentThread();
        while (!sign.compareAndSet(null, thread)){
            System.out.println("自旋获取失败，再次尝试");
        }
    }

    private void unlock(){
        Thread thread = java.lang.Thread.currentThread();
        sign.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "释放自旋锁");
    }



}
