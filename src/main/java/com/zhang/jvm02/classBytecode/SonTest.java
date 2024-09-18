package com.zhang.jvm02.classBytecode;

/**
 * @Desc:
 *  Father f = new Son();
 *  已知非静态成员变量的初始化过程
 *  1. 先执行父类构造
 *  2. 由于子类实例，指向父类引用,执行的print方法是子类方法,但此时子类还未执行构造方法,此时非静态成员变量x就是0
 *  3. 再执行子类构造
 *  4. 最后执行f.x,返回父类非静态变量x
 *
 * @Author：zhh
 * @Date：2024/9/10 16:08
 */
public class SonTest {
    public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);
    }
}
