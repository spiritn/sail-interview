package com.jun.code.stack;

/**
 * 利用数组来实现栈
 *
 * 不支持动态扩容
 * @author Jun
 * 创建时间： 2020/2/29
 */
public class StackArray {

    //数组
    private String[] items;

    //栈中元素个数
    private int count;

    //栈的大小
    private int n;

    public StackArray(int n){
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    /**
     * 往栈中放入一个元素
     */
    public boolean push(String item){
        //如果栈中元素已经满了，直接返回false。支持动态扩容的栈不常用，所以这里就实现不能扩容的栈了
        if(count == n){
            return false;
        }
        items[count++] = item;
        return true;
    }

    /**
     * 从栈中取出一个元素
     */
    public String pop(){
        if(count == 0){
            return null;
        }
        //取出下标为count-1的元素，并且元素个数count减一
        return items[--count];
    }

    public int size(){
        return count;
    }

}
