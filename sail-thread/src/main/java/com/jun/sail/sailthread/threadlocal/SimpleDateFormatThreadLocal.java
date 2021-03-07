package com.jun.sail.sailthread.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SimpleDateFormat是线程不安全的，所以把他放进ThreadLocal，每个线程拥有自己的SimpleDateFormat
 *
 * @author Jun
 * 创建时间： 2020/3/22
 */
public class SimpleDateFormatThreadLocal {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            final int seconds = i;
            executorService.submit(new Thread(() -> {
                Date date = new Date(1000 * seconds);
                //获取线程自己的SimpleDateFormat
                SimpleDateFormat dateFormat = ThreadLocalSafeFormat.dateFormatThreadLocal.get();
                System.out.println(dateFormat.format(date));
            }));
        }
    }

    static class ThreadLocalSafeFormat {

        public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            return dateFormat;
        });
    }
}
