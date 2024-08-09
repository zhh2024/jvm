package com.zhang.jvmzhh.reference;

/**
 * @Desc: 强引用
 * 除非GC Roots 断开此引用对象链接,不然不会被回收,哪怕内存溢出。
 * @Author：zhh
 * @Date：2024/8/9 10:19
 */
public class StrongReferenceTest {
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer ("Hello,尚硅谷");
        StringBuffer str1 = str;

        str = null;
        System.gc();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(str1);
    }
}
