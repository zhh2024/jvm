package com.zhang.jvm01.reference;

import java.lang.ref.SoftReference;

/**
 * @Desc: 软引用
 * -Xms10m -Xmx10m
 * 当内存足够时，不会回收软引用可达的对象。内存不够时，会回收软引用的可达对象
 * @Author：zhh
 * @Date：2024/8/9 10:22
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        //创建对象，建立软引用
        //SoftReference<User> userSoftRef = new SoftReference<User>(new User(1, "songhk"));
        //上面的一行代码，等价于如下的三行代码
        User u1 = new User(1,"songhk");
        SoftReference<User> userSoftRef = new SoftReference<User>(u1);
        //取消强引用
        u1 = null;
        //从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.out.println("---目前内存还不紧张---");
        System.gc();
        System.out.println("After GC:");
        //垃圾回收之后获得软引用中的对象,由于堆空间内存足够，不会回收软引用的可达对象。
        System.out.println(userSoftRef.get());
        System.out.println("---下面开始内存紧张了---");
        try {
            //堆内存放不下,开始触发GC
            byte[] b = new byte[1024 * 1024 * 10];
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //再次从软引用中获取数据,在报OOM之前，垃圾回收器会回收软引用的可达对象。
            System.out.println(userSoftRef.get());
        }
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
