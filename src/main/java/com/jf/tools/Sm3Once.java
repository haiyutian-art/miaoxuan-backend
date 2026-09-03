package com.jf.tools;

import com.jf.utils.Sm3;

/**
 * 本地一次性运行的小工具：输出 SM3(明文 + "jingfei") 摘要
 * 使用方法：在 IDE 中运行 main 方法，查看控制台输出
 */
public class Sm3Once {
    public static void main(String[] args) throws Exception {
        // 可修改为你要计算的临时口令明文
        String temp = "123456";
        String hash = Sm3.EncoderBySm3(temp);
        System.out.println(hash);
    }
}


