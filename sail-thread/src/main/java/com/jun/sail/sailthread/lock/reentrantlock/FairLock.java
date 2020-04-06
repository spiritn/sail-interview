package com.jun.sail.sailthread.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jun
 * 创建时间： 2020/3/31
 */
public class FairLock {

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        for (int i = 0; i < 10; i++) {
            new Thread(new PrintJob(printQueue),"线程" + i).start();

            try {
                //保证顺序启动线程
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class PrintJob implements Runnable{

    PrintQueue printQueue1;
    public PrintJob(PrintQueue printQueue1){
        this.printQueue1 = printQueue1;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始打印");
        printQueue1.printJob();
        System.out.println(Thread.currentThread().getName() + " 结束打印");
    }
}

class PrintQueue{
    //这里修改true false来演示公平锁和非公平锁
    private Lock queuelock = new ReentrantLock(false);

    public void printJob(){
        queuelock.lock();
        try {
            Long duration = (long) new Random().nextInt(3000);
            System.out.println(Thread.currentThread().getName() + " 正在第一次打印，需要 " + duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queuelock.unlock();
        }

        queuelock.lock();
        try {
            Long duration = (long) new Random().nextInt(3000);
            System.out.println(Thread.currentThread().getName() + " 正在第二次打印，需要 " + duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queuelock.unlock();
        }
    }
}
