package com.zhang.jvmzhh.garbageCollection;

import java.util.ArrayList;

/**
 * @Desc:
 *  -Xms8m -Xmx8m
 *  -XX:+HeapDumpOnOutOfMemoryError  这个参数的意思是当程序出现OOM的时候就会在当前工程目录生成一个dump文件
 * @Author：zhh
 * @Date：2024/8/6 15:37
 */
public class HeapDumpTest {
    byte[] buffer = new byte[1 * 1024 * 1024];

    public static void main(String[] args) {
        ArrayList<HeapDumpTest> list = new ArrayList<>();

        int count = 0;
        try{
            while(true){
                list.add(new HeapDumpTest());
                count++;
            }
        }catch (Throwable e){
            System.out.println("count = " + count);
            e.printStackTrace();
        }
    }
}
