package com.zhang.jvm01.vmstack.dynamicLink;

/**
 * @Desc: 多态下字节码指令方式是虚方法,表现为晚期绑定,需要在运行过程过,通过局部变量表,获取到对象
 *        再从对象中获取到方法区中的虚方法表,执行真正的方法。
 * @Author：zhh
 * @Date：2024/6/28 17:15
 */
public class DynamicLink {

    /**
     * 晚期绑定。编译期间,无法确定Animal是子类还是父类
     * @param animal
     */
    public void showAnimal(Animal animal) {
        animal.eat();//表现为：晚期绑定
    }

    /**
     * 晚期绑定。编译期间,无法确认接口的实现类
     * @param h
     */
    public void showHunt(Huntable h) {
        h.hunt();//表现为：晚期绑定
    }

}
