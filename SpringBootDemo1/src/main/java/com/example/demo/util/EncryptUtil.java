package com.example.demo.util;

import com.example.demo.common.Base64;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * 加密工具类
 * @author 赵金鑫
 * @date 2017年09月13日
 */
@Slf4j
public class EncryptUtil {


    /*md5加密*/
    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //自定义解密
    public static String decode(String encryptionString) throws IOException {
        byte[] byte_decode = Base64.decode(encryptionString);
        String s_decode = new String(byte_decode);
        String front = s_decode.substring(s_decode.length() - 2);
        String last = s_decode.substring(0, s_decode.length() - 2);
        return front + last;
    }

    //自定义加密
    public static String encrypt(String decodeString) {
        String front = decodeString.substring(0, 2);
        String last = decodeString.substring(2);
        String str = last + front;
        return Base64.encodeBytes(str.getBytes());
    }

    public static void main(String[] args) throws IOException {
        System.out.println(decode("LjE0MS41LjkxMTA="));
        System.out.println(encrypt("10.141.5.91"));
        System.out.println(encrypt("root"));
        System.out.println(encrypt("root"));
        System.out.println(MD5("root"));

    }

}
