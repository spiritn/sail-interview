package com.jun.code.heap;

public class HeapUtils {

    /**
     * 利用insert方法 进行堆化，即转化为一个堆
     */
    public static Heap buildHeapByInsert(int[] a) {
        // 因为是heap从1开始存储，所以需要多存一个
        Heap heap = new Heap(a.length + 1);
        // 把数组中每个值依次插入
        for (int i :a) {
            heap.insert(i);
        }
        return heap;
    }

    public static Heap buildHeap(int[] a) {
        for (int i = a.length / 2; i >= 1; i--) {
            heapify(a, a.length - 1, i);
        }

        Heap heap = new Heap(a.length);
        heap.setA(a);
        heap.setCount(a.length - 1);
        return heap;
    }

    private static void heapify(int[] a, int n, int i) {
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

}
