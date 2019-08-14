package com.zld.mall.redis.key;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 11:50
 */
public abstract class BasePrefix implements KeyPrefix{
    private int expireSeconds;
    private String prefix;
    public BasePrefix(String prefix){
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        //0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getName();
        return className+":"+prefix;
    }
}
