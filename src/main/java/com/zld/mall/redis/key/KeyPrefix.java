package com.zld.mall.redis.key;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 11:49
 */
public interface KeyPrefix {
    public int expireSeconds();
    public String getPrefix();
}
