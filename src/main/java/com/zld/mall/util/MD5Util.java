package com.zld.mall.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 描述：MD5的工具类
 *
 * @author lida
 * @time 2019/8/14 15:10
 */
public class MD5Util {
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1A2c3C4d";

    //明文密码做第一次md5
    public static String inputPassFromPass(String inputPass){
        String str = "abc" + salt.charAt(1) + salt.charAt(3) + inputPass + salt.charAt(5) +  salt.charAt(4);
        return md5(str);
    }

    //对一次md5再做一次md5
    public static String fromPassToDBPass(String fromPass, String salt){
        String str = "" + salt.charAt(1) + salt.charAt(3) + fromPass + salt.charAt(5) +  salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input, String salt){
        return fromPassToDBPass(inputPassFromPass(input),salt);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDbPass("123456","1A2c3C4d"));
}
}
