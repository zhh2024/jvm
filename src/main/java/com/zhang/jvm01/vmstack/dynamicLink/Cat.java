package com.zhang.jvm01.vmstack.dynamicLink;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/7/1 10:58
 */
public class Cat extends Animal implements Huntable{
    public Cat() {
        super();//表现为：早期绑定
    }

    public Cat(String name) {
        this();//表现为：早期绑定
    }

    @Override
    public void eat() {
        super.eat();//表现为：早期绑定
        System.out.println("猫吃鱼");
    }
    @Override
    public void hunt() {
        System.out.println("捕食耗子，天经地义");
    }
}
