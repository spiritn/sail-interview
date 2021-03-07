package com.jun.sail.sailthread.aqs;


import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 自己用一个AQS实现一个简单的线程协作器，
 * 这是一个类似于CountDownLatch的闩锁类，只不过个数为1，只需要触发signal即可。
 * 因为闩锁是共享性的，所以它使用shared获取和释放方法
 * <p>
 * 代码取自AQS源码注释中举例
 */
public class OneShotLatch {

    private Sync sync = new Sync();

    public void await() throws InterruptedException {
        // 调用AQS的acquireShared，这里的参数没有使用到，实际没啥意义
        sync.acquireSharedInterruptibly(1);
    }

    public void signal() {
        // 调用AQS的releaseShared
        sync.releaseShared(1);
    }


    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * if (tryAcquireShared(arg) < 0)
         * doAcquireSharedInterruptibly(arg);
         *
         * @return -1就会进入阻塞队列，1就是获取锁成功
         */
        @Override
        protected int tryAcquireShared(int arg) {
            // state int默认是0，不等于0说明release方法被执行了
            // 线程这里getState()不是0，就返回1，根据代码就不会进入阻塞队列，就相当于获取到锁了
            return getState() != 0 ? 1 : -1;
        }

        /**
         * @return true代表应该唤醒队列中的线程了，false代表还没放闸应该进入阻塞队列等待
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
                try {
                    oneShotLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 开闸放行");
            }).start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 开闸，不像countDownLatch需要每个线程倒数
        oneShotLatch.signal();
    }
}
