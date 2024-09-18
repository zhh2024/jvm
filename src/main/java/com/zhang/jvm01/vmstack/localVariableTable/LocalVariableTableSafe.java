package com.zhang.jvm01.vmstack.localVariableTable;

/**
 * @Desc: 局部变量表的安全问题
 * 具体问题具体分析
 * 1.  如果只有一个线程才可以操作此数据，则必是线程安全的。
 * 2.  如果有多个线程操作此数据，则此数据是共享数据。如果不考虑同步机制的话，会存在线程安全问题。
 * <p>
 * 对于方法变量来说如果对象是在内部产生，并在内部消亡，没有返回到外部，那么它就是线程安全的，反之则是线程不安全的
 * @Author：zhh
 * @Date：2024/7/1 14:58
 */
public class LocalVariableTableSafe {
    int num = 10;

    //s1的声明方式是线程安全的（只在方法内部用了）
    public static void method1() {
        //StringBuilder:线程不安全
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        //...
    }

    //sBuilder的操作过程：是线程不安全的（作为参数传进来，可能被其它线程操作）
    public static void method2(StringBuilder sBuilder) {
        sBuilder.append("a");
        sBuilder.append("b");
        //...
    }

    //s1的操作：是线程不安全的（有返回值，可能被其它线程操作）
    public static StringBuilder method3() {
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1;
    }

    //s1的操作：是线程安全的（s1自己消亡了，最后返回的只是s1.toString的一个新对象）
    public static String method4() {
        StringBuilder s1 = new StringBuilder();
        s1.append("a");
        s1.append("b");
        return s1.toString();
    }

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();

        new Thread(() -> {
            s.append("a");
            s.append("b");
        }).start();

        method2(s);
    }
}
