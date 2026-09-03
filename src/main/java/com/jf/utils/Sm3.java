package com.jf.utils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;

/**
 * SM3加密工具类
 * 用于密码加密，替换原来的MD5
 */
public class Sm3 {

    static {
        // 添加BouncyCastle作为安全提供者
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * SM3加密
     * @param source 待加密字符串
     * @return 加密后的字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String encode(String source) throws UnsupportedEncodingException {
        SM3Digest digest = new SM3Digest();
        byte[] sourceBytes = source.getBytes("UTF-8");
        digest.update(sourceBytes, 0, sourceBytes.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);
        return Hex.toHexString(result);
    }

    /**
     * 使用默认字符集对字符串进行SM3加密
     */
    public static String sm3Safe(String text) {
        try {
            return encode(text);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 利用SM3进行加密（带盐值）
     * @param str 待加密字符串
     * @return 加密后的字符串
     * @throws UnsupportedEncodingException 编码异常
     */
    public static String EncoderBySm3(String str) throws UnsupportedEncodingException {
        String salt = "jingfei";
        String newstr = encode(str.concat(salt));
        return newstr;
    }

    /**
     * 判断用户密码是否正确
     * @param newpasswd 用户输入的密码
     * @param oldpasswd 数据库中存储的密码
     * @return 是否匹配
     * @throws UnsupportedEncodingException 编码异常
     */
    public static boolean checkpassword(String newpasswd, String oldpasswd) throws UnsupportedEncodingException {
        if (EncoderBySm3(newpasswd).equals(oldpasswd))
            return true;
        else
            return false;
    }
}

