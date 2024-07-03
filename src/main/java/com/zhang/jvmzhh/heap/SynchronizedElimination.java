package com.zhang.jvmzhh.heap;

/**
 * @Desc: 同步消除
 * 1. 线程同步的代价是相当高的，同步的后果是降低并发性和性能。
 * 2. 在动态编译同步块的时候，JIT编译器可以借助逃逸分析来判断同步块所使用的锁对象是否只能够被一个线程访问而没有被发布到其他线程。
 * 3. 如果没有，那么JIT编译器在编译这个同步块的时候就会取消对这部分代码的同步。这样就能大大提高并发性和性能。这个取消同步的过程就叫同步省略，也叫锁消除。
 * @Author：zhh
 * @Date：2024/7/3 17:10
 */
public class SynchronizedElimination {
    /**
     *
     * 编译后字节码文件中并没有进行优化，可以看到加锁和释放锁的操作依然存在，同步省略操作是在解释运行时发生的
     */
    public void f() {
        Object hollis = new Object();
        synchronized(hollis) {
            System.out.println(hollis);
        }
        //下面是真实运行的
        /*public void f() {
            Object hellis = new Object();
            System.out.println(hellis);
        }*/
    }
}
