package com.zhang.jvm02.instructions;

/**
 * @Desc: 算术指令
 * @Author：zhh
 * @Date：2024/9/18 15:34
 */
public class Arithmetic {
    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        arithmetic.method1();
    }
    public void method1(){
        int i = 10;
        double j = i / 0.0;
        //无穷大
        System.out.println(j);

        double d1 = 0.0;
        double d2 = d1 / 0.0;
        //NaN: not a number
        System.out.println(d2);
    }

    public void method2(){
        float i = 10;
        float j = -i;
        i = -j;
    }

    public void method3(int j){
        int i = 100;
        //i = i + 10;
        i += 10;
    }
    public int method4(){
        int a = 80;
        int b = 7;
        int c = 10;
        return (a + b) * c;
    }

    public int method5(int i ,int j){
        return ((i + j - 1) & ~(j - 1));
    }

}
