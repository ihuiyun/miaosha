package com.zld.mall.redis.key;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/15 16:10
 */
public class MiaoShaUserKey extends BasePrefix{
    private static final int TOKEN_EXPIRE = 3600 * 24;

    public MiaoShaUserKey(int expire, String prefix) {
        super(expire, prefix);
    }

    public static MiaoShaUserKey token = new MiaoShaUserKey(TOKEN_EXPIRE,"tk");
}
