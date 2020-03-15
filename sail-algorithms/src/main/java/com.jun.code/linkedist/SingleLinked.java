package com.jun.code.linkedist;


/**
 * 实现单向链表
 *
 * @author Jun
 * 创建时间： 2020/2/10
 */
public class SingleLinked<E> implements ILinked<E> {

    public static void main(String[] args) {
        SingleLinked<String> listNode = new SingleLinked<>();

        listNode.add(0, "a");
        listNode.add("b");
        listNode.add("c");
        listNode.add("d");
        listNode.add("e");
        listNode.add("f");

        listNode.reverseLinked(listNode.first);

        System.out.println(listNode);
    }


    public Node<E> reverseLinked(Node<E> head) {
        Node<E> prev = null;
        Node<E> curr = head;

        //prev跟在curr后，逐步向后走
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        first = prev;
        return prev;

    }


    private int size = 0;

    private Node<E> first;

    static class Node<E> {

        E data;

        Node<E> next;

        Node(E e, Node<E> next) {
            this.data = e;
            this.next = next;
        }
    }


    @Override
    public E get(int index) {
        return node(index).data;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        linkAfter(e, size);
        return true;
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index);
        linkAfter(element, index);
    }

    /**
     * 插入时，要考虑插入头结点的特殊情况
     */
    private void linkAfter(E e, int index) {
        checkIndex(index);
        if (index == 0) {
            first = new Node<>(e, first);
        } else {
            //找到要插入位置的前一个结点
            Node<E> node = node(index - 1);
            //把新节点复制给前一个结点，承上启下
            node.next = new Node<>(e, node.next);
        }
        size++;
    }



    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is error");
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public int size() {
        return size;
    }


    private Node<E> node(int index) {
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node<E> node = first; node != null; node = node.next) {
            sb.append(node.data + " ");
        }
        sb.append("]");
        return sb.toString();
    }


}
