package com.jun.sail.jdk;

/**
 * @author Jun
 * 创建时间： 2020/4/6
 */
public class HelloImpl implements IHello {


    @Override
    public String sayHello(String str) {
        return "helloImpl: " + str;
    }

    @Override
    public String receiveDoor(String str) {
        return "helloImpl: " + str;
    }
}
