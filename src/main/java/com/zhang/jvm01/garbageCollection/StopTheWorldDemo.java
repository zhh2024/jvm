package com.zhang.jvm01.garbageCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc:
 * -Xms15m -Xmx15m
 * list.clear()后,buffer对象没被任何对象引用,当新生代内存空间不足时,会触发垃圾回收
 * @Author：zhh
 * @Date：2024/8/8 17:28
 */
public class StopTheWorldDemo {
    public static void main(String[] args) {
        WorkThread w = new WorkThread();
        PrintThread p = new PrintThread();
        w.start();
        p.start();
    }

    public static class WorkThread extends Thread {
        List<byte[]> list = new ArrayList<byte[]>();

        public void run() {
            try {
                while (true) {
                    for(int i = 0;i < 1000;i++){
                        byte[] buffer = new byte[1024];
                        list.add(buffer);
                    }

                    if(list.size() > 10000){
                        list.clear();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread {
        public final long startTime = System.currentTimeMillis();

        public void run() {
            try {
                while (true) {
                    // 每秒打印时间信息
                    long t = System.currentTimeMillis() - startTime;
                    Thread.sleep(1000);
                    System.out.println(t / 1000 + "." + t % 1000);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
