package com.jun.sail.sailthread.aqs;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自己用一个AQS实现一个简单的线程协作器，类似CountDownLatch个数为1
 */
public class OneShotLatch {

    private Sync sync = new Sync();

    public void await() {
        //调用AQS的acquireShared
        sync.acquireShared(0);
    }

    public void singal() {
        //调用AQS的releaseShared
        sync.releaseShared(1);
    }


    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            //-1会进入阻塞队列，1就是获取锁成功
            return getState() == 1 ? 1 : -1;
        }

        /**
         * @param arg
         * @return true代表应该唤醒队列中的线程了，false代表还没防闸应该进入阻塞队列等待
         */
        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }


    public static void main(String[] args) {
        OneShotLatch oneShotLatch = new OneShotLatch();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 尝试获取latch。。。。");
                oneShotLatch.await();
                System.out.println(Thread.currentThread().getName() + " 开闸放行");
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //开闸
        oneShotLatch.singal();


    }
}
