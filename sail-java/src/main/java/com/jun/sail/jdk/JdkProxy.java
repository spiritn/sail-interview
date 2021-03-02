package com.jun.sail.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Jun
 * 创建时间： 2020/4/6
 */
public class JdkProxy {

    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();

        //根据指定的参数创建代理对象
        IHello helloProxy = (IHello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), new Class<?>[]{IHello.class}, new LogInvocationHandler(hello));

        //helloProxy的类型是class com.sun.proxy.$Proxy0，父类是

        System.out.println(helloProxy.sayHello("处理事件"));
        System.out.println(helloProxy.receiveDoor("接受门禁"));
    }
}



/**
 * 代理类  实现InvocationHandler
 */
class LogInvocationHandler implements InvocationHandler{

    private IHello hello;

    public LogInvocationHandler(IHello hello){
        this.hello = hello;
    }

    //目标对象的所有方法都会被转发到这里，但是从Object继承的方法中只有hashcode(), equals(), toString()会转发到这里
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("sayHello".equals(method.getName())){
            System.out.println("log proxy start-----" + Arrays.toString(args));
        }

        return method.invoke(hello, args);
    }
}
