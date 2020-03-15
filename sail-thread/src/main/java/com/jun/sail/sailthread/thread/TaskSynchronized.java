package com.jun.sail.sailthread.thread;

/**
 * @author Jun
 * 创建时间： 2020/3/8
 */
public class TaskSynchronized {
    static Object object = new Object();
    static int count = 1;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                synchronized (object) {
                    //注意这里要用while循环
                    while (count != 1) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("a");
                    count = 2;
                    object.notifyAll();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                synchronized (object) {
                    while (count != 2) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("b");
                    count = 3;
                    object.notifyAll();
                }
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                synchronized (object) {
                    while (count != 3) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("c");
                    count = 1;
                    object.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }

}
