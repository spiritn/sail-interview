package com.jun.code.linear.linkedlist.impl;

import com.jun.code.linear.linkedlist.*;
/**
 * 实现双向链表,参考LinkedList（注意LinkedList实现了双向链表和队列）
 *
 * @author Jun
 * 创建时间： 2020/2/9
 */
public class DoublyLinked<E>  implements ILinked<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    public DoublyLinked(){}

    public static void main(String[] args) {
        DoublyLinked<String> linked = new DoublyLinked<>();
        linked.add("first");
        linked.add("second");
        linked.add("third");
        System.out.println(linked.toString());

        linked.add(1, "IndexSecond");
        System.out.println(linked);

        System.out.println(linked.lastIndexOf("IndexSecon0d"));
    }


    private static class Node<E>{
        private E data;

        private Node<E> prev;

        private Node<E> next;

        public Node(Node<E> prev, E data, Node<E> next){
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public boolean contains(Object o) {
       return indexOf(o) != -1;
    }

    public void clear() {
        last = first = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).data;
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        Node<E> node = node(index);
        E oldValue = node.data;
        node.data = element;
        return oldValue;
    }

    /**
     * 添加元素
     */
    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 根据索引添加元素
     * @param index
     * @param element
     */
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if(index == size){
            linkLast(element);
        }else {
            linkBefore(element, node(index));
        }
    }

    private void linkLast(E e){
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if(l == null){
            first = newNode;
        }else {
            l.next = newNode;
        }
        size++;
    }

    private void linkBefore(E e, Node<E> node){
        Node<E> pred = node.prev;
        Node<E> newNode = new Node<>(pred, e, node);
        node.prev = newNode;
        if(pred == null){
            first = newNode;
        }else {
            pred.next = newNode;
        }
        size++;
    }

    /**
     * 删除元素o
     */
    @Override
    public boolean remove(Object o) {
        if(o == null){
            for(Node<E> node = first; node != null; node = node.next){
                if(node.data == null){
                    removeNode(node);
                    return true;
                }
            }
        }else {
            for(Node<E> node = first; node != null; node = node.next){
                if(o.equals(node.data)){
                    removeNode(node);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据索引删除节点
     * @param index  索引
     */
    @Override
    public E remove(int index) {
       return removeNode(node(index));
    }

    private E removeNode(Node<E> node){
        Node<E> next = node.next;
        Node<E> prev = node.prev;
        //这里有4种情况：头节点、尾节点、只有一个节点、多个节点
        if(prev == null){
            first = next;
        }else {
            prev.next = next;
        }

        if(next == null){
            last = prev;
        }else {
            next.prev = prev;
        }
        //长度减一
        size--;
        return node.data;
    }

    /**
     * 查找元素首次出现的位置
     *
     * @return 返回元素在链表中首次出现的位置，不存在就返回-1
     */
    @Override
    public int indexOf(Object o) {
        int index = 0;
        //如果要查询的对象为null，就去查找null的
        if(o == null){
            for(Node<E> node = first; node != null; node = node.next){
                if(node.data == null){
                    return index;
                }
                index++;
            }
        }else {
            for(Node<E> node = first; node != null; node = node.next){
                if(o.equals(node.data)){
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    /**
     * 查找元素最后出现的位置
     *
     * @return 返回元素在链表中最后出现的位置，不存在就返回-1
     */
    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        if(o == null){
            for(Node<E> node = last; node != null; node = node.prev){
                if(node.data == null){
                    return index;
                }
                index--;
            }
        }else {
            for(Node<E> node = last; node != null; node = node.prev){
                if(o.equals(node.data)){
                    return index;
                }
                index--;
            }
        }
        return -1;
    }

    @Override
    public int size(){
        return size;
    }

    /**
     * 根据索引找到节点Node
     *
     * @param index 索引
     * @return Node<E>
     */
    private Node<E> node(int index){
        checkIndex(index);
        if(index <= size/2){
             Node<E> node = first;
             for(int i = 0; i < index; i++){
                 node = node.next;
             }
             return node;
        }else {
            Node<E> node = last;
            for(int i = size - 1; i > index; i--){
                node = node.prev;
            }
            return node;
        }
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("[");
        for(Node<E> e = first; e != null; e = e.next){
            str.append(e.data).append(" ");
        }
        str.append("]");
        return str.toString();
    }

    private void checkIndex(int index){
        if (index <0 || index >size){
            throw new IllegalArgumentException("index is error");
        }
    }





}
