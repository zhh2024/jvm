package com.zhang.jvm02.classLoadProcess;

/**
 * 哪些场景下不会生成<clinit>()方法?
 * 1. 不存在静态字段 或 静态代码块
 * 2. 静态字段没有做显示赋值
 * 3. 静态字段被final修饰,显示赋值是基础数据类型或者字符串,可以看成常量,会在链接阶段（准备阶段开辟内存空间的时候就赋值）
 * @Author: zhh
 * @Date: 2024/10/14 16:07
 */
public class InitializationTest1 {
    //场景1：对应非静态的字段，不管是否进行了显式赋值，都不会生成<clinit>()方法
    public int num = 1;
    //场景2：静态的字段，没有显式的赋值，不会生成<clinit>()方法
    public static int num1;
    //场景3：比如对于声明为static final的基本数据类型的字段，不管是否进行了显式赋值，都不会生成<clinit>()方法
    public static final int num2 = 1;
}
