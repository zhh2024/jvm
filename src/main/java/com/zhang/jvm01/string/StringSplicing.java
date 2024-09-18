package com.zhang.jvm01.string;

/**
 * @Desc: 字符串拼接
 * @Author：zhh
 * @Date：2024/8/5 14:43
 */
public class StringSplicing {

    public static void main(String[] args) {

    }

    /*
     * 查看反编译后的字节码文件,可以知道s1编译后就在常量池 abc
     * String s1 = "abc";
     * String s2 = "abc"
     */
    public void splicing01(){
        //编译期优化：等同于"abc"
        String s1 = "a" + "b" + "c";
        //"abc"一定是放在字符串常量池中，将此地址赋给s2
        String s2 = "abc";

        //true
        System.out.println(s1 == s2);
        //true
        System.out.println(s1.equals(s2));
    }

    /**
     * 从字节码角度来看：拼接前后有变量，都会使用到 StringBuilder类
     */
    public void splicing02(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        //编译期优化
        String s4 = "javaEE" + "hadoop";
        //如果拼接符号的前后出现了变量，则相当于在堆空间中new String()，具体的内容为拼接的结果：javaEEhadoop
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = s1 + s2;
        //true
        System.out.println(s3 == s4);
        //false
        System.out.println(s3 == s5);
        //false
        System.out.println(s3 == s6);
        //false
        System.out.println(s3 == s7);
        //false
        System.out.println(s5 == s6);
        //false
        System.out.println(s5 == s7);
        //false
        System.out.println(s6 == s7);
    }

    /**
     *  1. 字符串拼接操作不一定使用的是StringBuilder!
     *     如果拼接符号左右两边都是字符串常量或常量引用，则仍然使用编译期优化，即非StringBuilder的方式。
     *  2. 针对于final修饰类、方法、基本数据类型、引用数据类型的量的结构时，能使用上final的时候建议使用上。
     */
    public void splicing03(){
        final String s1 = "a";
        final String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;
        //true
        System.out.println(s3 == s4);
    }

    /**
     * 拼接操作与 append 操作的效率对比
     * 1.  体会执行效率：通过StringBuilder的append()的方式添加字符串的效率要远高于使用String的字符串拼接方式！
     * 2.  原因：
     *     1.  StringBuilder的append()的方式：
     *         自始至终中只创建过一个StringBuilder的对象
     *     2.  使用String的字符串拼接方式：
     *         创建过多个StringBuilder和String（调的toString方法）的对象，内存占用更大；
     *         如果进行GC，需要花费额外的时间（在拼接的过程中产生的一些中间字符串可能永远也用不到，会产生大量垃圾字符串）。
     * 3.  改进的空间：
     *         在实际开发中，如果基本确定要前前后后添加的字符串长度不高于某个限定值highLevel的情况下，建议使用构造器实例化：
     *        `StringBuilder s = new StringBuilder(highLevel); //new char[highLevel]`
     *         这样可以避免频繁扩容
     */
    public void splicing04(){
        long start = System.currentTimeMillis();
        //method1(100000);
        method2(100000);
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));
    }

    public void method1(int highLevel){
        String src = "";
        for(int i = 0;i < highLevel;i++){
            //每次循环都会创建一个StringBuilder、String
            src = src + "a";
        }

    }

    public void method2(int highLevel){
        //只需要创建一个StringBuilder
        StringBuilder src = new StringBuilder();
        for (int i = 0; i < highLevel; i++) {
            src.append("a");
        }
    }
}
