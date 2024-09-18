package com.zhang.jvm01.reference;

/**
 * @Desc: threadLocal作为key虽然是弱引用,但是被static修饰了,变成静态常量了,无法被回收
 * @Author：zhh
 * @Date：2024/8/9 10:55
 */
public class ThreadLocalTest {
    static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public void setThreadLocal(){
        threadLocal.set("zhh");
    }

    public void getThreadLocal(){
        threadLocal.get();
    }
}
