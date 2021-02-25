package com.jun.code.heap;

/**
 * @Author wangjun
 * @Date 2021/2/25
 **/
public class HeapTest {

    public static void main(String[] args) {
        int[] a = {2, 9, 8, 1, 6, 4, 5};
        Heap heap = Heap.buildHeap(a);
        System.out.println(heap);

        heap.removeMax();
        System.out.println(heap);

        heap.removeMax();
        System.out.println(heap);
    }



}
