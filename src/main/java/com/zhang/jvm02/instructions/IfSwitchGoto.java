package com.zhang.jvm02.instructions;

/**
 * @Desc: 控制转移指令
 * 不是int类型 ,会先执行比较指令,弹出栈顶两个元素进行比较,相等是0,大于是1,小于是-1,压入栈 。 然后再执行条件跳转指令
 * int类型 ,比较和条件跳转相结合
 * @Author：zhh
 * @Date：2024/9/19 10:43
 */
public class IfSwitchGoto {

    public static void main(String[] args) {
        IfSwitchGoto ifSwitchGoto = new IfSwitchGoto();
        ifSwitchGoto.swtich1(2);

    }

    /**
     * 条件跳转指令
     */
    public void compare1() {
        int a = 0;
        if (a != 0) {
            a = 10;
        } else {
            a = 20;
        }
    }

    public boolean compareNull(String str) {
        if (str == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 结合比较指令
     */
    public void compare2() {
        float f1 = 9;
        float f2 = 10;
        //true
        System.out.println(f1 < f2);
    }

    public void compare3() {
        int i1 = 10;
        long l1 = 20;
        System.out.println(i1 > l1);
    }

    public int compare4(double d) {
        if (d > 50.0) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 比较条件跳转指令
     */
    public void ifCompare1() {
        int i = 10;
        int j = 20;
        System.out.println(i > j);
    }

    public void ifCompare2() {
        short s1 = 9;
        byte b1 = 10;
        System.out.println(s1 > b1);
    }

    public void ifCompare3() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        //false
        System.out.println(obj1 == obj2);
        //true
        System.out.println(obj1 != obj2);
    }

    /**
     * 多条件分支跳转
     * 如果没有break的话,从指令来看,会一直往后case走,直到遇到break
     *
     * @param select
     */
    public void swtich1(int select) {
        int num;
        switch (select) {
            case 1:
                num = 10;
                System.out.println(num);
                break;
            case 2:
                num = 20;
                System.out.println(num);
                //break;
            case 3:
                num = 30;
                System.out.println(num);
                break;
            default:
                num = 40;
                System.out.println(num);
        }

    }

    public void swtich2(int select) {
        int num;
        switch (select) {
            case 100:
                num = 10;
                break;
            case 500:
                num = 20;
                //break; ]\


            case 200:
                num = 30;
                break;
            default:
                num = 40;
        }
    }

    /**
     * jdk7新特性：引入String类型
     * 为了计算效率,case的是String hash值, case相同的hash值,然后再进行equals
     *
     * @param season
     */
    public void swtich3(String season) {
        switch (season) {
            case "SPRING":
                break;
            case "SUMMER":
                break;
            case "AUTUMN":
                break;
            case "WINTER":
                break;
        }
    }

    /**
     * 无条件跳转指令
     */
    public void whileInt() {
        int i = 0;
        while (i < 100) {
            String s = "atguigu.com";
            i++;
        }
    }

    public void whileDouble() {
        double d = 0.0;
        while (d < 100.1) {
            String s = "atguigu.com";
            d++;
        }
    }

    public void printFor() {
        short i;
        for (i = 0; i < 100; i++) {
            String s = "atguigu.com";
        }

    }

    /**
     * 思考：如下两个方法的操作有何不同？
     * whileTest 和 forTest 编译后的指令一模一样
     * 但是whileTest可以继续使用i , forTest 不可以继续使用i
     */
    public void whileTest() {
        int i = 1;
        while (i <= 100) {

            i++;
        }
    }

    public void forTest() {
        for (int i = 1; i <= 100; i++) {

        }
    }

    /**
     * do while 和 while的区别
     */
    public void doWhileTest() {
        int i = 1;
        do {
            i++;
        } while (i <= 100);
    }
}
