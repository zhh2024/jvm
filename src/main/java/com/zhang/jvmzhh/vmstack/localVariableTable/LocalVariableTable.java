package com.zhang.jvmzhh.vmstack.localVariableTable;

import com.zhang.jvmzhh.classLoad.Parent;

/**
 * @Desc: 局部变量表
 * 方法a和方法b 是两个栈帧,每个栈帧维护了自己的局部变量表,所以方法a的i值并不会被方法b修改。
 * @Author：zhh
 * @Date：2024/6/7 10:07
 */
public class LocalVariableTable {

    private int num;

    public static void main(String[] args) {
        LocalVariableTable localVariableTable = new LocalVariableTable();
        localVariableTable.a();
    }

    public static void test(){
        //因为static方法栈帧局部变量表中没有this变量,所以无法调用this。
        //this.num;
        int nums = 10;
        double d = 100d;
        String s = "dd";
        int nums2 = 5;
    }
    public void a(){
        int i = 10;
        i++;
        b(i);
        System.out.println(i);
    }
    public void b(int i){
        i = 100;
        System.out.println(i);
    }

    /**
     * j变量 是int类型占用数组一个槽,但是作用域出了{}就没了,这个槽就会被复用，被parent占用，所以
     * 反编译后的数组小标是0 this,1 num,2 parent, 3 s。
     */
    public void multipleSlot(){
        int num = 10;
        if(num > 5){
            int j = 3;
        }
        Parent parent = new Parent();
        String s ="";
    }
}
