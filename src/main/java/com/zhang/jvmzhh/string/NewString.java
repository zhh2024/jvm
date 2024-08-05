package com.zhang.jvmzhh.string;

/**
 * @Desc:
 * @Author：zhh
 * @Date：2024/8/5 16:22
 */
public class NewString {
    public static void main(String[] args) {

    }

    /**
     * 字节码指令
     * 0 new #2 <java/lang/StringBuilder>
     * 3 dup
     * 4 invokespecial #3 <java/lang/StringBuilder.<init>>
     * 7 new #4 <java/lang/String>
     * 10 dup
     * 11 ldc #5 <a>
     * 13 invokespecial #6 <java/lang/String.<init>>
     * 16 invokevirtual #7 <java/lang/StringBuilder.append>
     * 19 new #4 <java/lang/String>
     * 22 dup
     * 23 ldc #8 <b>
     * 25 invokespecial #6 <java/lang/String.<init>>
     * 28 invokevirtual #7 <java/lang/StringBuilder.append>
     * 31 invokevirtual #9 <java/lang/StringBuilder.toString>
     * 34 astore_1
     * 35 return
     * ```
     * 答案是5个或6个
     * 字节码指令分析：
     * 1.  `0 new #2 <java/lang/StringBuilder>` ：拼接字符串会创建一个 StringBuilder 对象
     * 2.  `7 new #4 <java/lang/String>` ：创建 String 对象，对应于 new String(“a”)
     * 3.  `11 ldc #5 <a>` ：在字符串常量池中放入 “a”（如果之前字符串常量池中没有 “a” 的话）
     * 4.  `19 new #4 <java/lang/String>` ：创建 String 对象，对应于 new String(“b”)
     * 5.  `23 ldc #8 <b>` ：在字符串常量池中放入 “b”（如果之前字符串常量池中没有 “b” 的话）
     * 6.  `31 invokevirtual #9 <java/lang/StringBuilder.toString>` ：调用 StringBuilder 的 toString() 方法，会生成一个 String 对象
     */
    public void newString(){
        String str = new String("a") + new String("b");
    }
}
