package com.jun.code.heap;

public class HeapUtils {

    public static int[] topK(int[] a, int k) {
        // 先建立k的小顶堆
        int[] kArr = new int[k + 1];
        for (int i = 0; i < k; i++) {
            kArr[i + 1] = a[i];
        }
        buildHeap(kArr);

        for (int i = k; i < a.length; i++) {
            if (kArr[1] < a[i]) {
                kArr[1] = a[i];
                buildHeap(kArr);
            }
        }
        return kArr;
    }

    private static void heapify2(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            // 这里其实就是找出最小值
            if (2 * i <= n && a[2 * i] < a[i]) {
                maxPos = 2 * i;
            }
            if (2 * i + 1 <= n && a[2 * i + 1] < a[maxPos]) {
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


    public static void sort(int[] a) {
        // 1.建堆
        buildHeap(a);
        // 2.把堆顶元素放在数组末尾，重新堆化
        int k = a.length - 1;
        while (k > 1) {
            int temp = a[k];
            a[k] = a[1];
            a[1] = temp;
            k--;
            heapify(a, k, 1);
        }
    }

    /**
     * 利用insert方法 进行堆化，即转化为一个堆
     * 时间复杂度nLogN,没有下面的方法好
     */
    public static Heap buildHeapByInsert(int[] a) {
        // 因为是heap从1开始存储，所以需要多存一个
        Heap heap = new Heap(a.length + 1);
        // 把数组中每个值依次插入
        for (int i : a) {
            heap.insert(i);
        }
        return heap;
    }

    /**
     * 从1开始将数组堆化
     * 时间复杂度是n
     */
    public static Heap buildHeap(int[] a) {
        for (int i = a.length / 2; i >= 1; i--) {
            heapify2(a, a.length - 1, i);
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
