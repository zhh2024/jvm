package com.zhang.jvmzhh.classLoad;

/**
 * @Desc: 获取类加载器的途径
 * @Author：zhh
 * @Date：2024/5/28 14:55
 */
public class GetCustomClassLoader {
    public static void main(String[] args) {
        try {
            //1.获取指定类的类加载器
            Class<?> aClass = Class.forName("com.zhang.jvmzhh.classLoad.GetCustomClassLoader");
            aClass.getClassLoader();
            //2.获取当前类的类加载器
            GetCustomClassLoader.class.getClassLoader();
            //3.获取当前线程的类加载器
            Thread.currentThread().getContextClassLoader();
            //4.获取系统类加载器
            ClassLoader.getSystemClassLoader();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
