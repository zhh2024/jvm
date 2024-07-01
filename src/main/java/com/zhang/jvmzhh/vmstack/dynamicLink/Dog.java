package com.zhang.jvmzhh.vmstack.dynamicLink;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/7/1 10:57
 */
public class Dog extends Animal implements Huntable{

    /**
     * 早期绑定
     */
    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    /**
     * 早期绑定
     */
    @Override
    public void hunt() {
        System.out.println("捕食耗子，多管闲事");
    }
}
