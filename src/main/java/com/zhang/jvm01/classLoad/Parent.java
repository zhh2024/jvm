package com.zhang.jvm01.classLoad;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/5/24 15:57
 */
public class Parent {
    static String name = "zhh";

    static {
        System.out.println(name);
    }
}
