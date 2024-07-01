package com.zhang.jvmzhh.vmstack.localVariableTable;

/**
 * @Desc: 局部变量表大小编译期间就确认了, 内部是数组,4个字节的占一个slot(槽),8个字节的占两个slot(槽)。
 *        所以局部变量一开始就应该初始化。
 * @Author：zhh
 * @Date：2024/7/1 14:49
 */
public class LocalVariableTableSize {

    public void init(){
        int i = 10;
        int j = 20;
        int c = i+ j;
    }

    /**
     * 小于4个字节的,编译后都会转换成int
     */
    public void slotSize(){
        short a = 10;
        byte b = 2;
        boolean flag =false;
    }
}
