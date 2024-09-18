package com.zhang.jvm01.nativeMemory;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * @Desc: 本地内存的OOM:  OutOfMemoryError: Direct buffer memory
 * @Author：zhh
 * @Date：2024/8/1 17:33
 */
public class BufferTest2 {
    private static final int BUFFER = 1024 * 1024 * 20;

    public static void main(String[] args){
        ArrayList<ByteBuffer> list = new ArrayList<>();

        int count = 0;
        try {
            while(true){
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
                list.add(byteBuffer);
                count++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            System.out.println(count);
        }
    }
}
