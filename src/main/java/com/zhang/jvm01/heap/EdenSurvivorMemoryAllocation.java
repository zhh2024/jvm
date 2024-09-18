package com.zhang.jvm01.heap;

/**
 *  -Xms600m -Xmx600m
 *
 *  -XX:NewRatio=  设置新生代与老年代的比例。默认值是2.
 *  -XX:SurvivorRatio= 设置新生代中Eden区与Survivor区的比例。默认值是8
 *  -XX:-UseAdaptiveSizePolicy 关闭自适应的内存分配策略  （暂时用不到）
 *  -Xmn 设置新生代的空间的大小。 （一般不设置）
 * @Desc:
 * @Author：zhh
 * @Date：2024/7/3 11:07
 */
public class EdenSurvivorMemoryAllocation {
    public static void main(String[] args) {
        System.out.println("内存分配......~");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
