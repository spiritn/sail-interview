package com.jun.code.singleinstance;

/**
 * 利用静态内部类来实现单例莫模式
 *
 * @author Jun
 * 创建时间： 2020/3/29
 */
public class Single4StaticClass {

    //第一步 构造器私有
    private Single4StaticClass(){}

    //第二步 提供静态方法返回实例
    public static Single4StaticClass getInstance(){
        return SingleInstanceHolder.instance;
    }

    /**
     * 静态内部类，注意类和instance都是private static修饰
     */
    private static class SingleInstanceHolder{
        private static final Single4StaticClass instance = new Single4StaticClass();
    }


}
