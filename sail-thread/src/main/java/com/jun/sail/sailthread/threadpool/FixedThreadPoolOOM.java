package com.jun.sail.sailthread.threadpool;

import java.util.concurrent.*;

/**
 * 演示java的内置线程池
 *
 * @author Jun
 * 创建时间： 2020/3/21
 */
public class FixedThreadPoolOOM {

    // FixedThreadPool底层是一个核心线程和最大线程一样的，使用LinkedBlockingQueue无界队列，所以容易发生OOM
    private static ExecutorService threadPool = Executors.newFixedThreadPool(1);

    private static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    // new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

    private static ExecutorService stealingPool = Executors.newWorkStealingPool();


    public static void main(String[] args) {
        // fixedThreadPool();
        System.out.println("获得JAVA虚拟机可用的最大CPU处理器数量：" + Runtime.getRuntime().availableProcessors());

        fixedThreadPool();
    }

    // Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
    private static void fixedThreadPool() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cachedThreadPool.execute(new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    // 让每个线程一直sleep,这样任务就不会马上被执行完，会不停放进队列
                    // 另外还要设置-Xmx200m，否则触发OOM时间太长
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    private static void scheduledExecutorService() {
        Thread thread = new Thread(() -> {
            PhotoInfoDto d = new PhotoInfoDto();
            System.out.println(Thread.currentThread().getName());
        });

        // 延迟5秒后执行任务
        scheduledExecutorService.schedule(thread, 2, TimeUnit.SECONDS);

        //在1s后，然后每隔3秒执行任务
        scheduledExecutorService.scheduleAtFixedRate(thread, 1, 3, TimeUnit.SECONDS);
    }

}
