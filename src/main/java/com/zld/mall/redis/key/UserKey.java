package com.zld.mall.redis.key;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 14:19
 */
public class UserKey extends BasePrefix{
    public UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
