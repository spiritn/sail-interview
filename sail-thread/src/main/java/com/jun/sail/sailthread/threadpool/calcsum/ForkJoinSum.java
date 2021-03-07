package com.jun.sail.sailthread.threadpool.calcsum;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int n = 1000000;

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinCalc forkJoinCalc = new ForkJoinCalc(0, n);
        // invoke:同步提交，有返回值，任务执行完成后返回,会重新抛出子线程的异常
        // submit：异步提交，有返回值，调用线程立即返回
        // execute：异步提交，无返回值，调用线程立即返回
        Long result = forkJoinPool.invoke(forkJoinCalc);

        System.out.println("sum result= " + result + ", spend ms: " + (System.currentTimeMillis() - start));
    }


    public static class ForkJoinCalc extends RecursiveTask<Long> {

        private long start;
        private long end;

        private static final long THRESHOLD = 1000;

        public ForkJoinCalc(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if ((end - start) < THRESHOLD) {
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            }

            long mid = (start + end) / 2;
            ForkJoinCalc left = new ForkJoinCalc(start, mid);
            left.fork();
            ForkJoinCalc right = new ForkJoinCalc(mid + 1, end);
            right.fork();

            return right.join() + left.join();
        }
    }
}
