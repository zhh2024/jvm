package com.zhang.jvm02.instructions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Desc: 异常处理指令
 * @Author：zhh
 * @Date：2024/9/19 15:07
 */
public class Exception {

    public static void main(String[] args) {
        //hello
        System.out.println(func());
    }

    public void throwZero(int i){
        if(i == 0){
            throw new RuntimeException("参数值为0");
        }
    }
    public void throwOne(int i) throws RuntimeException, IOException {
        if(i == 1){
            throw new RuntimeException("参数值为1");
        }
    }
    public void throwArithmetic() {
        int i = 10;
        int j = i / 0;
        System.out.println(j);
    }

    public void tryCatch(){
        try{
            File file = new File("d:/hello.txt");
            FileInputStream fis = new FileInputStream(file);

            String info = "hello!";
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    /**
     * 思考：如下方法返回结果为多少？
     *  0 ldc #19 <hello>
     *  2 astore_0
     *  3 aload_0
     *  4 astore_1
     *  5 ldc #20 <zhh>
     *  7 astore_0
     *  8 aload_1
     *  9 areturn
     *
     *  ldc #19 <hello> 将 hello压入操作数栈中
     *  astore_0 栈顶出栈，放入局部变量表 （下标0）
     *  aload_0 局部变量表下标0 元素，压入操作数栈
     *  astore_1 栈顶出栈，放入局部变量表 （下标1）  此时局部变量表 有两个元素 ，下标 0,1 都是 hello
     *  ldc #20 <zhh>  将 zhh压入操作数栈中
     *  astore_0 栈顶出栈，放入局部变量表 （下标0）, 此时局部量表下标0的元素会被覆盖成zhh
     *  aload_1  局部变量表下标1 元素，压入操作数栈
     *  areturn 栈顶出栈，此时出栈的元素是 局部变量表下标1 的元素hello
     *
     *  可以看出再return之前,finally已经执行了,但是返回的值是之前的值，因为复制了一份副本
     * @return
     */
    public static String func() {
        String str = "hello";
        try{
            return str;
        }
        finally{
            str = "zhh";
            System.out.println("01" +str);
        }
    }

}
