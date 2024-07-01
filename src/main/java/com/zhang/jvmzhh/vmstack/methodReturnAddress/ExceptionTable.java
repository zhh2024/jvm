package com.zhang.jvmzhh.vmstack.methodReturnAddress;

import java.io.*;

/**
 * @Desc: 方法有两种返回方式 1.正常返回 2.异常返回,异常返回不会带有任何返回值
 * @Author：zhh
 * @Date：2024/7/1 14:16
 */
public class ExceptionTable {

    /**
     * 抛出异常
     * @param filePath
     * @throws IOException
     */
    public void readFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * 捕捉异常,编译后会有异常处理表
     * 反编译字节码文件，可得到 Exception table
     * 起始PC ：字节码指令起始地址
     * 结束PC ：字节码指令结束地址
     * 跳转PC ：出现异常跳转至地址为 9 的指令执行
     * 异常类型 ：捕获异常的类型
     */
    public void getFile(){
        try {
            readFile("zhh.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
