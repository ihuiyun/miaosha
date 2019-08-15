package com.zld.mall.util;

import java.util.UUID;

/**
 * 描述：生成uuid
 *
 * @author lida
 * @time 2019/8/15 16:02
 */
public class UUIDUtil {
    public static String uuid(){
        //生成的原生的UUID带有-，要将其去掉
        return UUID.randomUUID().toString().replace("-","");
    }
}
