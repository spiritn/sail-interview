package com.jun.code.linear.queue;

public class QueueTest {
    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo(5);
        queueDemo.enqueue(2);
        queueDemo.enqueue(3);
        queueDemo.enqueue(5);
        queueDemo.enqueue(1);
        queueDemo.enqueue(9);
        queueDemo.dequeue();
        queueDemo.dequeue();
        queueDemo.enqueue(6);
        queueDemo.dequeue();
        queueDemo.dequeue();
        queueDemo.dequeue();
        queueDemo.dequeue();

    }
}
