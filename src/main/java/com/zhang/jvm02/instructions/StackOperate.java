package com.zhang.jvm02.instructions;

/**
 * @Desc: 操作数栈指令
 * 深入理解栈的数据结构,更容易理解相关指令涉及到的栈的出栈和进栈
 * @Author：zhh
 * @Date：2024/9/18 18:07
 */
public class StackOperate {
    public static void main(String[] args) {
        StackOperate stackOperate = new StackOperate();
        System.out.println(stackOperate.nextIndex());
        System.out.println(stackOperate.index);
    }
    public void print(){
        Object obj = new Object();
        //String info = obj.toString();
        obj.toString();
    }

    public void foo(){
        bar();
    }
    public long bar(){
        return 0;
    }

    /**
     * 0 aload_0
     * 1 dup
     * 2 getfield #2 <com/zhang/jvm02/instructions/StackOperate.index : J>
     * 5 dup2_x1
     * 6 lconst_1
     * 7 ladd
     * 8 putfield #2 <com/zhang/jvm02/instructions/StackOperate.index : J>
     * 11 lreturn
     *
     * 通过编译后的指令可以看出
     * getfield 入栈  将字段index值存入
     * dup2_x1  入栈  dup2赋值 long占用两个slot x代表插入, 2+1 就是插入的位置，离栈顶的位置是3,目前栈中只有 this, 0(long) ,栈顶是0,总共占用了三个槽,插入的位置离栈顶
     *                3,意思就是插入到this下面，就是栈底
     * lconst_1 入栈  将常量1压入栈
     * ladd     出栈 出栈两个,两个元素相加, 相加后的值为1 ,入栈
     * putfield 出栈 出栈两个,this和将相加后的值，放入字段中
     * lreturn, 出栈 此时栈顶元素是dup2_x1的值为0
     *
     * 同理 ++index,先加,再dup2_x1,最终返回的栈顶元素就是1了
     * @return
     */
    public long nextIndex() {
        return index++;
    }

    private long index = 0;
}
