package com.zhang.jvmzhh.vmstack.dynamicLink.methodOverride;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/7/1 11:22
 */
public class Father {
    public Father() {
        System.out.println("father的构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}
