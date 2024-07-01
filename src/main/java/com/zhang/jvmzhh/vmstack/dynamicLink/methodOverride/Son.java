package com.zhang.jvmzhh.vmstack.dynamicLink.methodOverride;

/**
 * @Desc: 在JVM中看方法重写的本质,编译期间无法确认实现的是父类方法还是子类方法,需要从下往上找。
 *        但是每次执行都从下往上找很浪费时间,所以在类加载初始化阶段,会初始化虚方法表,这样执行执行虚方法表指向的类方法即可。
 * @Author：zhh
 * @Date：2024/7/1 11:22
 */
public class Son extends Father{
    public Son() {
        //invokespecial
        super();
    }

    public Son(int age) {
        //invokespecial
        this();
    }

    //不是重写的父类的静态方法，因为静态方法不能被重写！
    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private" + str);
    }

    public void show() {
        //invokestatic
        showStatic("atguigu.com");
        //invokestatic
        super.showStatic("good!");
        //invokespecial
        showPrivate("hello!");
        //invokespecial
        super.showCommon();
        //invokevirtual
        showFinal();//因为此方法声明有final，不能被子类重写，所以也认为此方法是非虚方法。

        /**
         * 虚方法如下：
         */
        //invokevirtual 没有显示的加super.编译器认为你可能调用子类的showCommon(即使son子类没有重写)，所以编译期间确定不下来，就是虚方法。
        showCommon();
        //invokevirtual 无法确认父类是否有info()
        info();

    }

    public void info() {

    }

    public void display(Father f) {
        f.showCommon();
    }

    public static void main(String[] args) {
        Son so = new Son();
        so.show();
    }
}
