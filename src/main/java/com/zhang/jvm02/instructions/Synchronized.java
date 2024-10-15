package com.zhang.jvm02.instructions;

/**
 * @Desc: 同步控制指令
 * synchronized 作用在方法上, 方法上的访问标识会带有synchronized , 方法内部指令不变
 * synchronized 作用在方法内部, 方法内部指令会多出 monitorenter,monitorexit ,且默认有异常表，捕捉任何异常,以防发生异常无法monitorexit
 * @Author：zhh
 * @Date：2024/9/19 18:27
 */
public class Synchronized {

    private int i = 0;
    public void add(){
        i++;
    }


    private Object obj = new Object();
    public void subtract(){

        synchronized (obj){
            i--;
        }
    }


    public synchronized void subtract02(){
        i--;

    }
}
