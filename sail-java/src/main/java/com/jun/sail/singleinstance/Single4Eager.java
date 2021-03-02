package com.jun.sail.singleinstance;

/**
 * 饿汉式实现单例模式
 *
 * @author Jun
 * 创建时间： 2020/3/29
 */
public class Single4Eager {

    //第一步 构造器私有
    private Single4Eager(){}

    //直接创建静态的示例，缺点就是不能懒加载，如果用不到浪费空间
    private static Single4Eager instance = new Single4Eager();

    //第二步 提供静态方法返回实例
    public static Single4Eager getInstance(){
        return instance;
    }

}
