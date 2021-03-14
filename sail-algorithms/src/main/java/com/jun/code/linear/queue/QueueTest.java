package com.jun.code.linear.queue;

public class QueueTest {
    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo(5);
        queueDemo.enqueue(2);
        queueDemo.enqueue(3);
        queueDemo.enqueue(5);
        queueDemo.enqueue(1);
        System.out.println(queueDemo);
        queueDemo.enqueue(9);
        System.out.println(queueDemo);
        queueDemo.dequeue();
        System.out.println(queueDemo);
        queueDemo.dequeue();
        System.out.println(queueDemo);
        queueDemo.enqueue(6);
        System.out.println(queueDemo);
        queueDemo.dequeue();
        System.out.println(queueDemo);
        queueDemo.dequeue();
        System.out.println(queueDemo);
        queueDemo.dequeue();
        System.out.println(queueDemo);
        queueDemo.dequeue();
        System.out.println(queueDemo);

    }
}
