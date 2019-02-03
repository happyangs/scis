package com.jcohy.scis.utils;

import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * Created by Bryant on 2019.2.3
 */
public class MD5Util {
    /**
     * 给字符串进行MD5加密
     *
     * @param source
     * @return
     */
    public static String md5Hashing(String source) {
        if (Objects.nonNull(source)) {
            return DigestUtils.md5DigestAsHex(source.getBytes()).toUpperCase();
        }
        return null;
    }

    public static void main(String[] args) {
        String source = "hj9527";
        String md5 = md5Hashing(source);
        System.out.println(md5);
    }
}
