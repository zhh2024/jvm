package com.zhang.jvm01.nativeMemory;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/8/1 17:28
 */
public class BufferTest {
    /**
     * 1GB
     */
    private static final int BUFFER = 1024 * 1024 * 1024;

    public static void main(String[] args){
        //直接分配本地内存空间
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("直接内存分配完毕，请求指示！");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        System.out.println("直接内存开始释放！");
        byteBuffer = null;
        System.gc();
        scanner.next();
    }
}
