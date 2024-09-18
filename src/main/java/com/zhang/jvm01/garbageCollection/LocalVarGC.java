package com.zhang.jvm01.garbageCollection;

/**
 * @Desc: 测试对象作用域,作用范围,出栈后的引用对象,必然会被回收
 * @Author：zhh
 * @Date：2024/8/8 14:35
 */
public class LocalVarGC {

    public static void main(String[] args) {
        LocalVarGC local = new LocalVarGC();
        local.localvarGC5();
    }
    public void localvarGC1() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        System.gc();
    }

    public void localvarGC2() {
        byte[] buffer = new byte[10 * 1024 * 1024];
        buffer = null;
        System.gc();
    }

    /**
     *  查看字节码 实例方法局部变量表第一个变量
     *  肯定是实际上索引为1的位置是buffer在占用着，执行 System.gc() 时，栈中还有 buffer 变量指向堆中的字节数组，所以没有进行GC
     */
    public void localvarGC3() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public void localvarGC4() {
        {
            byte[] buffer = new byte[10 * 1024 * 1024];
        }
        int value = 10;
        System.gc();
    }

    public void localvarGC5() {
        localvarGC1();
        System.gc();
    }

}
