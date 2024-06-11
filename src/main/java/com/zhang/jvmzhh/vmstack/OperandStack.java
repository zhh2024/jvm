package com.zhang.jvmzhh.vmstack;

/**
 * @Desc: 操作数栈
 * @Author：zhh
 * @Date：2024/6/7 15:22
 */
public class OperandStack {

    public static void main(String[] args) {
        OperandStack operandStack = new OperandStack();
        operandStack.test1(100);
    }

    public int test1(int num1){
        int num2 = 10;
        int num3 = num2 + num1;
        int num4 = 50;
        return num3;
    }

    /**
     * 类型转换问题
     */
    public void test2(){
        byte b = 1;
        int a =2;
    }
}
