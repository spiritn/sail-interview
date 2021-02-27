package com.jun.code.singleinstance;

/**
 * 枚举实现单例
 * 1.防止反射调用构造器
 * 2.任何一个readObject方法，不管是显式的还是默认的，它都会返回一个新建的实例，这个新建的实例不同于该类初始化时创建的实例。
 * 当然这个问题也是可以解决的，对于实例控制，枚举类型优于readResolve
 *
 * @author Jun
 * 创建时间： 2020/3/29
 */
public class Single4Enum {

    //第一步 构造器私有
    private Single4Enum(){}

    //第二步 提供静态方法返回实例
    public static Single4Enum getInstance(){
        return InstanceEnum.INSTANCE.getSingle4Enum();
    }

    //其实利用枚举的一个单例来保证，通过他获取的也是单例
    private enum InstanceEnum{
        //这是个枚举对象，天生是单例的，等会要通过他来获取single4Enum
        INSTANCE;

        private Single4Enum single4Enum;

        InstanceEnum(){
            single4Enum = new Single4Enum();
        }

        public Single4Enum getSingle4Enum(){
            return single4Enum;
        }
    }
}
