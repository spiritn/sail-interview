package com.jun.sail.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Cglib是无法代理final修饰的方法的
 *
 * @author Jun
 * 创建时间： 2020/4/6
 */
public class CglibProxy {

    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");


        // 通过CGLIB动态代理获取代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonImpl.class);
        enhancer.setCallback(new MyMethodInterceptor());
        //personProxy的类型是com.jun.sail.sailjava.aop.impl.PersonImpl$$EnhancerByCGLIB$$bf73fe16
        PersonImpl personProxy = (PersonImpl) enhancer.create();

        personProxy.sayHello("你好");
        personProxy.sayOthers("大家好");

    }
}



class MyMethodInterceptor implements MethodInterceptor {

    /**
     * 这里注意参数和jdk动态代理的区别
     * @param o 是代理对象
     * @param args 方法参数
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        if("sayHello".equals(method.getName())){
            System.out.println("yes");
        }

        System.out.println("log proxy start-----" + Arrays.toString(args));

        return methodProxy.invokeSuper(o, args);
    }
}
