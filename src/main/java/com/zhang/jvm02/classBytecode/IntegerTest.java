package com.zhang.jvm02.classBytecode;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/9/10 15:38
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer x = 5;
        int y = 5;

        System.out.println(x == y);

        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);


        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);

    }
}

