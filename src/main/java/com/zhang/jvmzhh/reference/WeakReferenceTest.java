package com.zhang.jvmzhh.reference;

import java.lang.ref.WeakReference;

/**
 * @Desc: 虚引用
 * 只要发生GC，弱引用对象就会被回收。
 * @Author：zhh
 * @Date：2024/8/9 10:35
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        //构造了弱引用
        WeakReference<User> userWeakRef = new WeakReference<User>(new User(1, "zhh"));
        //从弱引用中重新获取对象
        System.out.println(userWeakRef.get());

        System.gc();
        // 不管当前内存空间足够与否，都会回收它的内存
        System.out.println("After GC:");
        //重新尝试从弱引用中获取对象
        System.out.println(userWeakRef.get());
    }

    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;

        @Override
        public String toString() {
            return "[id=" + id + ", name=" + name + "] ";
        }
    }
}
