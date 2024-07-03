package com.zhang.jvmzhh.heap;

/**
 * @Desc: 标量替换 本质就是方法内部的对象如果未发生逃逸,就将对象的成员变量,变成该方法内部的局部变量。jvm默认开启逃逸分析和标量替换
 * -Xmx100m -Xms100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * <p>
 * JVM会在栈上分配那些不会逃逸的对象，这在理论上是可行的，但是取决于JVM设计者的选择。
 * Oracle Hotspot JVM中并未这么做（是因为HotSpot实现了标量替换），这一点在逃逸分析相关的文档里已经说明
 * 所以可以明确在HotSpot虚拟机上，所有的对象实例都是创建在堆上。
 * @Author：zhh
 * @Date：2024/7/3 17:20
 */
public class ScalarReplace {
    public static class User {
        public int id;
        public String name;
    }

    public static void alloc() {
        //未发生逃逸
        User u = new User();
        u.id = 5;
        u.name = "www.atguigu.com";
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
    }
}
