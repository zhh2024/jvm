package com.zhang.jvmzhh.string;

/**
 * @Desc:
 * 1. intern是一个native方法，调用的是底层C的方法
 * 2. 字符串常量池池最初是空的，由String类私有地维护。在调用intern方法时，
 *    如果池中已经包含了由equals(object)方法确定的与该字符串内容相等的字符串，则返回池中的字符串地址。
 *    否则，该字符串对象将被添加到池中，并返回对该字符串对象的地址。
 *    JDK6 ：正常眼光判断即可
 *    new String() 即在堆中
 *    str.intern() 则把字符串放入常量池中
 *    JDK7及后续版本
 *    new String() 即在堆中
 *    str.intern() 把把new String()地址引用放入常量池中
 *    JDK7以后的版本是为了节省内存空间,不会重新复制字符串,字符串常量池只是存的原字符串的地址
 * 3. 如果不是用双引号声明的String对象，可以使用String提供的intern方法：
 *    intern方法会从字符串常量池中查询当前字符串是否存在，若不存在就会将当前字符串放入常量池中。比如：
 *    String myInfo = new string("I love you").intern();
 *
 * 4. 也就是说，如果在任意字符串上调用String.intern方法，那么其返回结果所指向的那个类实例，必须和直接以常量形式出现的字符串实例完全相同。
 *    因此，下列表达式的值必定是true
 *    ("a"+"b"+"c").intern()=="abc"
 *
 * 5. 通俗点讲，Interned String就是确保字符串在内存里只有一份拷贝，这样可以节约内存空间，加快字符串操作任务的执行速度。
 *    注意，这个值会被存放在字符串内部池（String Intern Pool）
 * @Author：zhh
 * @Date：2024/8/5 16:21
 */
public class StringIntern {

    public static void main(String[] args) {
        //通过字节码可以看出,最终StringBuilder会调用toString方法,但是toString方法并不会将"11"放入字符串常量池
        String s3 = new String("1") + new String("1");
        //在字符串常量池中生成对象"11"
        String s5 = s3.intern();
        String s4 = "11";
        // s3 是堆中的 "ab" ，s4 是字符串常量池中的 "ab"(JDK7后此时字符串常量池存放的是s3地址)  true
        System.out.println(s3 == s4);
        // s5 是从字符串常量池中取回来的引用，当然和 s4 相等  true
        System.out.println(s5 == s4);
    }

    static final int MAX_COUNT = 1000 * 10000;
    static final String[] arr = new String[MAX_COUNT];

    /**
     * 结论：对于程序中大量存在存在的字符串，尤其其中存在很多重复字符串时，使用intern()可以节省内存空间。
     * 调用了intern()方法使用了字符串常量池里的字符串，那么前面堆里的字符串便会被GC掉，这也是intern省内存的关键原因
     */
    public void StringIntern02(){
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {
            //arr[i] = new String(String.valueOf(data[i % data.length]));
            arr[i] = new String(String.valueOf(data[i % data.length])).intern();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }

}
