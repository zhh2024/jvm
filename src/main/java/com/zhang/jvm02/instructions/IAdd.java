package com.zhang.jvm02.instructions;

/**
 * @Desc: 自增指令
 * 不参与运算的情况下  i++ 和 ++i  没有区别,循环条件里面是等价的
 * 参与运算的情况下 i++ 和 ++i 的区别 : i++  先取出值到操作数栈，局部变量再自增,但操作数栈还是原来的值
 *                                 ++i  局部变量先自增,再取出值到操作数栈,操作数栈就是自增后的值
 * @Author：zhh
 * @Date：2024/9/18 14:21
 */
public class IAdd {
    public static void main(String[] args) {
        IAdd iAdd = new IAdd();
        iAdd.m3();
    }
    public void m1() {
        int i = 10;
        i++;
    }

    public void m2() {
        int i = 10;
        ++i;
    }

    public void m3() {
        int i = 10;
        int a = i++;

        int j = 20;
        int b = ++j;
        //10
        System.out.println(a);
        //21
        System.out.println(b);
    }

    public void m4() {
        int i = 10;
        i = i++;
        System.out.println(i);
    }
}
