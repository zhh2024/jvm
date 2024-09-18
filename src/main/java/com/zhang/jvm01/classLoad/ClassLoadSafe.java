package com.zhang.jvm01.classLoad;

/**
 * @Desc: 类加载过程中的安全问题, 测试clinit是否会被执行多次
 * @Author：zhh
 * @Date：2024/5/24 16:27
 */
public class ClassLoadSafe {

    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"线程正在进行Class Load...");
            new StaticClassLoad();
            System.out.println(Thread.currentThread().getName() +"线程Class Load结束");
        },"thread1").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName() +"线程正在进行Class Load...");
            new StaticClassLoad();
            System.out.println(Thread.currentThread().getName() +"线程Class Load结束");
        },"thread2").start();
    }
}
