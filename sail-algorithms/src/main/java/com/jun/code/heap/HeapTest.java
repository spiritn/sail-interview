package com.jun.code.heap;

import java.util.Arrays;

/**
 * @Author wangjun
 * @Date 2021/2/25
 **/
public class HeapTest {

    public static void main(String[] args) {
        int[] a = {2, 4,9, 8, 1, 6, 7, 5};
        // HeapUtils.sort(a);
        // System.out.println(Arrays.toString(a));
        int[] topK = HeapUtils.topK(a, 3);
        System.out.println(Arrays.toString(topK));


        // Heap heap = HeapUtils.buildHeapByInsert(a);
        // System.out.println(heap);
        //
        // heap.removeMax();
        // System.out.println(heap);
        //
        // heap.removeMax();
        // System.out.println(heap);
    }


}
