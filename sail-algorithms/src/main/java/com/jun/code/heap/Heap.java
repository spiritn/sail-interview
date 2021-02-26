package com.jun.code.heap;

/**
 * @Author wangjun
 * @Date 2021/2/25
 **/
public class Heap {

    /**
     * 堆已经存储的数据个数
     */
    private int count;

    /**
     * 堆可以存储的最大数据个数
     */
    private int n;

    /**
     * 数组，从1开始存储数据
     */
    private int[] a;

    public Heap(int capacity) {
        a = new int[capacity];
        n = capacity;
        count = 0;
    }

    /**
     * 插入操作，对于堆来说也就是插入和删除堆顶两个操作
     * 往上堆化
     */
    public void insert(int data) {
        // 已经满了
        if (count >= n) {
            return;
        }

        a[count++] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            // 交换swap两个
            int temp = a[i / 2];
            a[i / 2] = a[i];
            a[i] = temp;

            // 往上堆化
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count <= 0) {
            return;
        }
        a[1] = a[count--];
        heapify(a, n, 1);
    }

    /**
     * 利用insert方法 进行堆化，即转化为一个堆
     */
    public Heap heapifyByInsert(int[] a) {
        // 因为是heap从1开始存储，所以需要多存一个
        Heap heap = new Heap(a.length + 1);
        for (int i : a) {
            heap.insert(i);
        }

        return heap;
    }

    public static Heap buildHeap(int[] a) {
        Heap heap = new Heap(a.length);
        heap.setA(a);
        heap.setCount(a.length);

        for (int i = a.length / 2; i >= 1; i--) {
            heap.heapify(a, a.length - 1, i);
        }
        return heap;
    }

    /**
     * 往下堆化
     * logN
     */
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            // 这里其实就是找出最大值
            if (2 * i <= n && a[2 * i] > a[i]) {
                maxPos = 2 * i;
            }
            if (2 * i + 1 <= n && a[2 * i + 1] > a[maxPos]) {
                maxPos = 2 * i + 1;
            }
            if (maxPos == i) {
                break;
            }
            int temp = a[maxPos];
            a[maxPos] = a[i];
            a[i] = temp;

            i = maxPos;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        // heap从1开始存储
        for (int i = 1; i <= count; i++) {
            sb.append(a[i]).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public void setA(int[] a) {
        this.a = a;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
