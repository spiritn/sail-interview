package com.jun.code.linear.linkedlist;

/**
 * @author Jun
 * 创建时间： 2020/2/10
 */
public interface ILinked<E> {

    E get(int index);

    E set(int index, E element);

    boolean contains(Object o);

    boolean add(E e);

    void add(int index, E element);

    boolean remove(Object o);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    int size();

}
