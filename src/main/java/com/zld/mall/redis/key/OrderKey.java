package com.zld.mall.redis.key;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 14:19
 */
public class OrderKey extends BasePrefix{
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
