package com.jcohy.scis.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 加密模块
 */
public class EncryptDecrypt {
    /**
     * 盐值
     */
    private static final String SALT = "dfsad@#%$@TDGDF%$#%@#%WFRGFDHJKcvxznmfdsgdfgs2432534fgdf46t";
    /**
     * 内部密钥
     */
    private static final int[] KEY = {
            23, 22, 24, 4, 51, 26, 37, 27, 24, 6, 26, 38, 29, 35, 18, 21, 14, 3, 12, 4, 41, 39, 18, 44, 54, 21, 33, 35, 31, 22, 34, 53, 51, 44, 8, 12, 3, 0, 28, 1, 48, 9, 51, 57, 20, 44, 27, 3, 16, 48
    };

    /**
     * 16进制映射表
     */
    private static final Map MMP = new HashMap(12);

    static {
        short i = 10;
        for (; i < 16; i++) {
            MMP.put(i, (char) ('A' + i - 10));
            MMP.put((char) ('A' + i - 10), i);
        }
    }

    /**
     * 生成密钥
     *
     * @param len 密钥长度
     * @return
     */
    public static int[] generateKey(int len) {
        int ceiling = SALT.length();
        int[] key = new int[len];
        for (int i = 0; i < len; i++) {
            key[i] = (int) (Math.random() * ceiling);
        }
        return key;
    }

    /**
     * 加密
     *
     * @param original
     * @return
     */
    public static byte[] encrypt(byte[] original) {
        byte[] keyByte = new byte[KEY.length];
        /**
         * 获取加密字节数组
         */
        for (int i = 0; i < KEY.length; i++) {
            keyByte[i] = (byte) SALT.charAt(KEY[i]);
        }
        /**
         * 加密
         */
        int k = 0;
        byte[] encryptByte = new byte[original.length];
        for (int i = 0; i < original.length; i++) {
            encryptByte[i] = (byte) (original[i] ^ keyByte[k++ % keyByte.length]);
        }

        return encryptByte;
    }

    /**
     * 解密
     *
     * @param original
     * @return
     */
    public static byte[] decrypt(byte[] original) {
        return encrypt(original);
    }

    /**
     * 对字符串加密
     * 由于解码时 byte数组不一定符合规范 所以解码算法可能会改变字节数组的值 所以只能返回字节数组 而不能返回字符串
     *
     * @param original
     * @return
     */
    public static byte[] encryptString(String original) {
        return encrypt(original.getBytes());
    }

    /**
     * 对字符串解密
     *
     * @param original
     * @return
     */
    public static String decryptToString(byte[] original) {
        return new String(encrypt(original));
    }

    /**
     * 加密成16进制字符串
     *
     * @param original
     * @return
     */
    public static String encryptToHex(byte[] original) {
        //先加密
        byte[] bytes = encrypt(original);
        /**
         * 将加密的字节数组转换成16进制字符串
         */
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < bytes.length; i++) {
            short a = (short) (bytes[i] & 15);
            short b = (short) ((bytes[i] & (15 << 4)) >>> 4);
            if (b < 10) {
                stringBuffer.append(b);
            } else {
                stringBuffer.append(MMP.get(b));
            }

            if (a < 10) {
                stringBuffer.append(a);
            } else {
                stringBuffer.append(MMP.get(a));
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 从16进制字符串解密
     *
     * @param original
     * @return
     */
    public static byte[] decryptFromHex(String original) {
        /**
         * 将16进制字符串转换成字节数组
         */
        byte[] bytes = new byte[original.length() / 2];
        int len = 0;
        for (int i = 0; i < original.length(); i += 2) {
            short a, b;
            if (original.charAt(i) >= '0' && original.charAt(i) <= '9') {
                a = (short) (original.charAt(i) - '0');
                a <<= 4;
            } else {
                a = (short) MMP.get(original.charAt(i));
                a <<= 4;
            }

            if (original.charAt(i + 1) >= '0' && original.charAt(i + 1) <= '9') {
                b = (short) (original.charAt(i + 1) - '0');
            } else {
                b = (short) MMP.get(original.charAt(i + 1));
            }
            bytes[len++] = (byte) (a + b);
        }
        //解密字节数组
        return decrypt(bytes);
    }

    /**
     * 将字符串加密成16进制字符串
     *
     * @param original
     * @return
     */
    public static String encryptStringToHex(String original) {
        return encryptToHex(original.getBytes());
    }

    /**
     * 从16进制字符串解密成原字符串
     *
     * @param original
     * @return
     */
    public static String decryptStringFromHex(String original) {
        return new String(decryptFromHex(original));
    }

    public static void main(String[] args) {
        String original = "RMS123456";

        String encry = encryptStringToHex(original);
        System.out.println("加密后:"+encry);
        String decry = decryptStringFromHex(encry);
        System.out.println("解密后:"+decry);
    }

}
