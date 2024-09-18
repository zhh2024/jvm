package com.zhang.jvm01.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @Desc: 虚引用
 * 1.  也称为“幽灵引用”或者“幻影引用”，是所有引用类型中最弱的一个
 * 2.  一个对象是否有虚引用的存在，完全不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它和没有引用几乎是一样的，随时都可能被垃圾回收器回收。
 * 3.  它不能单独使用，也无法通过虚引用来获取被引用的对象。当试图通过虚引用的get()方法取得对象时，总是null 。即通过虚引用无法获取到我们的数据
 * 4.  为一个对象设置虚引用关联的唯一目的在于跟踪垃圾回收过程。比如：能在这个对象被收集器回收时收到一个系统通知。
 * 5.  虚引用必须和引用队列一起使用。虚引用在创建时必须提供一个引用队列作为参数。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象后，
 *     将这个虚引用加入引用队列，以通知应用程序对象的回收情况。
 * 6.  由于虚引用可以跟踪对象的回收时间，因此，也可以将一些资源释放操作放置在虚引用中执行和记录。
 * @Author：zhh
 * @Date：2024/8/9 10:41
 */
public class PhantomReferenceTest {
    /**
     * 当前类对象的声明
     */
    public static PhantomReferenceTest obj;
    /**
     * 引用队列
     */
    static ReferenceQueue<PhantomReferenceTest> phantomQueue = null;

    public static void main(String[] args) {
        Thread t = new CheckRefQueue();
        //设置为守护线程：当程序中没有非守护线程时，守护线程也就执行结束。
        t.setDaemon(true);
        t.start();
        phantomQueue = new ReferenceQueue<PhantomReferenceTest>();
        obj = new PhantomReferenceTest();
        //构造了 PhantomReferenceTest 对象的虚引用，并指定了引用队列
        PhantomReference<PhantomReferenceTest> phantomRef = new PhantomReference<PhantomReferenceTest>(obj, phantomQueue);
        try {
            //不可获取虚引用中的对象
            System.out.println(phantomRef.get());
            System.out.println("第 1 次 gc");
            //将强引用去除
            obj = null;
            //第一次进行GC,由于对象可复活，GC无法回收该对象
            System.gc();
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用");
            }
            System.out.println("第 2 次 gc");
            obj = null;
            System.gc(); //一旦将obj对象回收，就会将此虚引用存放到引用队列中。
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 是 null");
            } else {
                System.out.println("obj 可用");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 触发了GC操作，将对象回收了，同时将会触发第二个操作就是将待回收的对象存入到引用队列中。
     */
    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (phantomQueue != null) {
                    PhantomReference<PhantomReferenceTest> objt = null;
                    try {
                        objt = (PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (objt != null) {
                        System.out.println("追踪垃圾回收过程：PhantomReferenceTest实例被GC了");
                    }
                }
            }
        }
    }

    /**
     * 是会在第一次GC时被调用,finalize()方法只能被调用一次！
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类的finalize()方法");
        obj = this;
    }

}
