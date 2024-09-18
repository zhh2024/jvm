package com.zhang.jvm01.classLoad;

/**
 * @Desc: 类加载初始化过程就是执行clinit()方法,对static变量赋值和执行static方法。
 * @Author：zhh
 * @Date：2024/5/24 14:53
 */
public class StaticClassLoad extends Parent{

    private String name;

    //linking之prepare: age = "" --> initial: "" --> "add"
    static String age = "ddd";

    //final static修饰的字段,编译时就已经让入常量池了,不会在类加载准备的过程中去赋值
    final static String address ="beijing";

    final int ages = 20000;

    int time = 10;


    public static void main(String[] args) {
        System.out.println("123456");


    }

    static {
        //不加if会编译报错Initializer must be able to complete normally ,初始化程序(clinit()方法)必须能够正常完成，因为while true 初始化无法正常结束
        if (true){
            System.out.println(Thread.currentThread().getName()+":"+address);
            //模拟类加载很长时间
            while (true){

            }
        }

    }
    static int b = 23;

}
