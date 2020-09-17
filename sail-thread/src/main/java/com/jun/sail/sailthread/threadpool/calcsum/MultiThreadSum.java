package com.jun.sail.sailthread.threadpool.calcsum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 用来实现多线程计算1-100的和，常见面试题
 * 此处使用线程池，每个线程实现Callable接口
 */
public class MultiThreadSum {

    public static void main(String[] args) {
        int n = 100000;
        int numThread = 3;
        ExecutorService threadPool = Executors.newFixedThreadPool(numThread);

        long start = System.currentTimeMillis();

        List<Future<Long>> list = new ArrayList<>();
        for (int i = 0; i < numThread; i++) {
            // 如5个线程计算1到100，则第一个线程就是0-20，第二个线程就是20-40
            Future<Long> longFuture = threadPool.submit(new SumTread(i * n / numThread, (i + 1) * n / numThread));
            list.add(longFuture);
        }
        threadPool.shutdown();

        long result = 0;
        for (Future<Long> future : list) {
            try {
                result += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 单独处理n，包括n
        result += n;
        System.out.println("sum result= " + result + ", spend ms: " + (System.currentTimeMillis() - start));
    }


    public static class SumTread implements Callable<Long> {
        private long start;
        private long end;

        public SumTread(long start, long end) {
            this.start = start;
            this.end = end;
        }

        /**
         * 返回[start,end)的和
         */
        @Override
        public Long call() throws Exception {
            System.out.println("start to calc sum [" + start + ", " + end + ")");
            long sum = 0;
            for (long i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }
    }

}
