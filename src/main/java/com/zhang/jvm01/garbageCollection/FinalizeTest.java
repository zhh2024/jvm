package com.zhang.jvm01.garbageCollection;

/**
 * @Desc: finalize()发生垃圾回收时触发,只会在首次被回收时触发,如果被救回,后续垃圾回收不会再触发,此方法只能被调用一次
 * @Author：zhh
 * @Date：2024/8/6 10:15
 */
public class FinalizeTest {

    //类变量，属于 GC Root
    public static FinalizeTest obj;

    /**
     * 发生垃圾回收时触发,只会在首次被回收时触发,如果被救回,后续垃圾回收不会再触发,此方法只能被调用一次
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("调用当前类重写的finalize()方法");
        //当前待回收的对象在finalize()方法中与引用链上的一个对象obj建立了联系
        obj = this;
    }

    public static void main(String[] args) {
        try {
            obj = new FinalizeTest();
            //对象第一次成功拯救自己
            obj = null;
            System.gc();//调用垃圾回收器
            System.out.println("第1次 gc");
            // 因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
            System.out.println("第2次 gc");
            // 下面这段代码与上面的完全相同，但是这次自救却失败了
            obj = null;
            System.gc();
            // 因为Finalizer线程优先级很低，暂停2秒，以等待它
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
