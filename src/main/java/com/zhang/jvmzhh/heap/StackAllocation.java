package com.zhang.jvmzhh.heap;

/**
 * @Desc: 栈上分配测试, 不添加参数, 默认开启了逃逸分析。 其实本质是标量替换, JVM默认开启了标量替换 -XX:+EliminateAllocations
 * 未发生逃逸,本质是标量替换。所以可以明确在HotSpot虚拟机上，所有的对象实例都是创建在堆上。
 * 虽然User没有成员变量,也就意味着main方法局部变量表不存在User成员变量,但是main方法栈帧也不会存User对象。
 * -Xmx128m -Xms128m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * @Author：zhh
 * @Date：2024/7/3 17:03
 */
public class StackAllocation {
    /**
     * 验证逃逸分析,如果分配在栈上,不会打印GC日志,且无需堆内存分配,消耗时间短
     * 如果分配到了栈上,发生了内存分配,会打印GC日志,消耗时间长
     *
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        // 查看执行时间
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为： " + (end - start) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private static void alloc() {

        User user = new User();
        user.getName();
    }

    static class User {
        public void getName() {

        }
    }
}
