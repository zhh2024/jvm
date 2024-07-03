package com.zhang.jvmzhh.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Desc: 设置堆内存为 300M,很快就OOM了。观察Visual GC ,查看年轻代和老年代的内存实时状况。
 * @Author：zhh
 * @Date：2024/7/2 17:04
 */
public class OOM {
    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while(true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));
        }
    }
}
class Picture{
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
