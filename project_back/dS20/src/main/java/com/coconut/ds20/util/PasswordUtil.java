package com.coconut.ds20.util;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/2/13 17:08
 * File: PasswordUtil
 * Project: dS9
 */

import java.security.MessageDigest;

/**
 * 密码加密工具
 * 不可逆加密
 * 彩虹表
 */
public class PasswordUtil {
    public static final String encrypt(String password,String salt){
        try {
            // MessageDigest是封装md5算法的工具对象还支持SHA算法
            MessageDigest md = MessageDigest.getInstance("SHA");
            // 通过digest拿到的任意字符串，得到的bytes都是等长的
            byte[] bytes = md.digest((password+salt).getBytes("utf-8"));
            return toHex(bytes);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 二进制的加密字符串转成16进制
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {
        // 把toHex的字符串从二进制 转换成16进制
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder result = new StringBuilder(bytes.length * 2);
        // 循环判断是为了补位操作
        for (int i = 0; i < bytes.length; i++) {
            result.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]); // 高4位
            result.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return result.toString();
    }
}
