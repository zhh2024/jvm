package com.zhang.jvmzhh.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 测试MinorGC 、 MajorGC、FullGC
 * -Xms60m -Xmx60m -XX:NewRatio=2  -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * @Author：zhh
 * @Date：2024/7/3 14:39
 */
public class GCDetails {
    public static void main(String[] args) {
        //testGc();
        oldGen();
    }

    /**
     * 打印Gc日志
     */
    public static void testGc() {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "atguigu.com";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数为：" + i);
        }
    }

    /**
     * 直接进入oldGen,eden放不下
     * 60m堆大小 2:1  oldGen分到40m。YoungGen分到 20m  eden:survivor0:survivor1 是 8:1:1 eden也就是 16m。
     * 16<对象<40即可。
     * <p>
     * 通过日志可以看出,直接将20480k一步到位放到了oldGen
     */
    public static void oldGen() {
        byte[] bytes = new byte[1024 * 1024 * 20];
    }
}
