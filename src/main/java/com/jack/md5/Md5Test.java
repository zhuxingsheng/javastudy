package com.jack.md5;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Md5是一个16字节长度的数组，将16字节的数组每四个字节一组
 * Created by Jack on 2019/3/2.
 */
public class Md5Test {

    public static void main(String[] args) {
        byte[] bytes = Md5Test.md5("value");
        System.err.println("length:"+bytes.length);
        System.err.println(Arrays.toString(bytes));

        byte[]digest = bytes;
        for (int h=0;h<4;h++) {
            Long k = ((long) (digest[3 + h * 4] & 0xFF) << 24)
                    | ((long) (digest[2 + h * 4] & 0xFF) << 16)
                    | ((long) (digest[1 + h * 4] & 0xFF) << 8)
                    | (digest[h * 4] & 0xFF);
            System.err.println(""+k);
        }
        System.err.println("oxff"+0XFF);
        System.err.println("oxff " + Integer.toBinaryString(0xFF));
        System.err.println("oxff"+(1 & 0XFF));
    }

    private static byte[] md5(String value) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        md5.reset();
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        md5.update(bytes);
        return md5.digest();
    }
}
