package com.zhang.jvmzhh.classLoad;

import java.io.FileNotFoundException;

/**
 * @Desc: 类的加载是双亲委派模式,CustomClassLoader的父类加载器是 AppClassLoader,它已经能完成了加载，就不会使用CustomClassLoader
 *        所以为了能触发CustomClassLoader,CustomClassLoader的父类及以上都必须加载失败。
 * @Author：zhh
 * @Date：2024/5/28 14:15
 */
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null) {
                System.out.println("自定义加载器也无法加载。。。。。");
                throw new FileNotFoundException();
            } else {
                //defineClass和findClass搭配使用
                return defineClass(name, result, 0, result.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException(name);
    }
    //自定义流的获取方式
    private byte[] getClassFromCustomPath(String name) {
        //从自定义路径中加载指定类:细节略
        //如果指定路径的字节码文件进行了加密，则需要在此方法中进行解密操作。
        return null;
    }



    public static void main(String[] args) throws ClassNotFoundException {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> clazz = customClassLoader.loadClass("dddd");
        try {
            //Class<?> clazz = Class.forName("com.zhang.jvmzhh.classLoad.StaticClassLoad", true, customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
