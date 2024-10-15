package com.zhang.jvm02.classLoadProcess;

import org.junit.Test;

/**
 * @Desc:
 * 关于类的被动使用，即不会进行类的初始化操作，即不会调用<clinit>()
 * 1. 当访问一个静态字段时，只有真正声明这个字段的类才会被初始化。
 *    当通过子类引用父类的静态变量，不会导致子类初始化
 * 2. 通过数组定义类引用，不会触发此类的初始化
 * 说明：没有初始化的类，不意味着没有加载！
 * @Author：zhh
 * @Date：2024/10/15 17:02
 */
public class PassiveUse1 {
    @Test
    public void test1(){
        System.out.println(Child.num);
    }

    @Test
    public void test2(){
        //数组只是声明了数据类型,并不会触发类的初始化操作
        Parent[] parents = new Parent[10];
        System.out.println(parents.getClass());
        System.out.println(parents.getClass().getSuperclass());
        //此时数据下标,创建对象才会触发类的初始化操作
        parents[0] = new Parent();
        parents[1] = new Parent();
    }
}

class Parent{
    static{
        System.out.println("Parent的初始化过程");
    }

    public static int num = 1;
}

class Child extends Parent{
    static{
        System.out.println("Child的初始化过程");
    }
}
