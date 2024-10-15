package com.zhang.jvm02.classLoadProcess;

import org.junit.Test;

import java.util.Random;

/**
 * @Desc:
 * 3. 当使用类、接口的静态字段时(final修饰特殊考虑)，比如，使用getstatic或者putstatic指令。（对应访问变量、赋值变量操作）,也会触发类的使用
 * 设置参数 -XX:+TraceClassLoading，可以追踪类的加载信息并打印出来
 * @Author：zhh
 * @Date：2024/10/15 10:41
 */
public class ActiveUse2 {
    @Test
    public void test1(){
        //System.out.println(User.num);
        //System.out.println(User.num1);
        System.out.println(User.num2);
    }

    @Test
    public void test2(){
        //System.out.println(CompareA.NUM1);
        System.out.println(CompareA.NUM2);
    }
}

class User{
    static{
        System.out.println("User类的初始化过程");
    }

    public static int num = 1;
    public static final int num1 = 1;
    public static final int num2 = new Random().nextInt(10);

}

interface CompareA{
    /**
     * 接口没有静态代码块,故而使用静态字段来进行测试
     */
    Thread t = new Thread(){
        {
            System.out.println("CompareA的初始化");
        }
    };

    int NUM1 = 1;
    int NUM2 = new Random().nextInt(10);

}
