package com.example.eebighomework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类，使用 MD5 算法对字符串进行加密
 */
public class EncryptUtil {
    /**
     * 对字符串进行加密
     * @param s 待加密的字符串
     * @return 加密后的字符串，如果加密失败则返回 null
     */
    public static String encrypt(String s) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] resultByteArray = messageDigest.digest(s.getBytes("UTF-8"));
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param byteArray 待转换的字节数组
     * @return 转换后的十六进制字符串
     */
    private static String byteArrayToHex(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : byteArray) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}