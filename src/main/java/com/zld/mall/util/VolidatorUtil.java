package com.zld.mall.util;

import org.thymeleaf.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 16:11
 */
public class VolidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src){
        if(StringUtils.isEmpty(src)){
            return false;
        }

        Matcher m = mobile_pattern.matcher(src);
        return  m.matches();
    }
}
