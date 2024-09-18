package com.zhang.jvm01.vmstack.operandStack;

/**
 * @Desc: 操作数栈, 4个字节占一个槽,8个字节占两个槽。
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

}
