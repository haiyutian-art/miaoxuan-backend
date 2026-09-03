package com.jf.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.util.DigestUtils;

/**
 * MD5加密工具类
 * 用于密码加密
 */
public class Md5 {

    /**
     * MD5加密
     * @param source 待加密字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException 算法异常
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String encode(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(source.getBytes("UTF-8"));
        byte[] result = md5.digest();
        return bytesToHex(result);
    }

    /**
     * 字节数组转十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 使用默认字符集对字符串进行MD5加密
     */
    public static String md5Safe(String text) {
        try {
            return encode(text);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 利用MD5进行加密（带盐值）
     * @param str 待加密字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException 算法异常
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String salt = "jingfei";
        String newstr = DigestUtils.md5DigestAsHex((str.concat(salt)).getBytes());
        return newstr;
    }

    /**
     * 判断用户密码是否正确
     * @param newpasswd 用户输入的密码
     * @param oldpasswd 数据库中存储的密码
     * @return 是否匹配
     * @throws NoSuchAlgorithmException 算法异常
     * @throws UnsupportedEncodingException 编码异常
     */
    public static boolean checkpassword(String newpasswd, String oldpasswd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (EncoderByMd5(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
} 