package com.jun.sail.sailjava.aop.cglib;

/**
 * @author Jun
 * 创建时间： 2020/4/6
 */
public class PersonImpl {

    public String sayHello(String str) {
        return "PersonImpl: " + str;
    }


    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     */
    final public String sayOthers(String name) {
        System.out.println("PersonImpl.sayOthers" + name);
        return null;
    }



}
