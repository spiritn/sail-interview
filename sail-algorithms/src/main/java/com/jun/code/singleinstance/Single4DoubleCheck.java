package com.jun.code.singleinstance;

/**
 * 双重检查锁来实现单例模式
 * @author Jun
 * 创建时间： 2020/3/29
 */
public class Single4DoubleCheck {

    //第一步 构造器私有
    private Single4DoubleCheck(){}

    //static volatile来修饰，先不初始化
    private static volatile Single4DoubleCheck instance = null;

    /**
     *  第二步 提供静态方法返回实例
     *  双重判断是否null，锁对象为当前对象
     */

    public static Single4DoubleCheck getInstance(){
        if(instance == null){
            synchronized (Single4DoubleCheck.class){
                if(instance == null){
                    instance = new Single4DoubleCheck();
                }
            }
        }
        return instance;
    }


}
