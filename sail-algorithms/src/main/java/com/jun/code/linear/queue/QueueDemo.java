package com.jun.code.linear.queue;


public class QueueDemo {

    private int[] item;

    private int head;

    /**
     * 指向最后一个数据的下一个位置
     */
    private int tail;

    private int count;

    public QueueDemo(int capacity) {
        item = new int[capacity];
    }

    public void enqueue(int data) {
        // 队列满了 这里会浪费一个空间
        if ((tail + 1) % item.length == head) {
            return;
        }
        item[tail++] = data;
        if (tail == item.length) {
            tail = 0;
        }
    }

    public int dequeue() {
        if (head == tail) {
            return 0;
        }
        int data = item[head];
        item[head++] = 0;
        if (head == item.length) {
            head = 0;
        }
        return data;
    }

}
