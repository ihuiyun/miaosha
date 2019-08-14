package com.zld.mall.redis;

import com.alibaba.fastjson.JSON;
import com.zld.mall.redis.key.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 10:17
 */
@Component
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 与Redis数据库的get操作
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix keyPrefix, String key, Class<T> tClass){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成数据库真正的key
            String realKey = keyPrefix.getPrefix() + key;
            String str = jedis.get(realKey);
            T t = stringToBean(str, tClass);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 与redis数据库set操作
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix keyPrefix, String key, T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0)
                return false;
            //生成数据库真正的key
            String realKey = keyPrefix.getPrefix() + key;
            int seconds = keyPrefix.expireSeconds();
            if (seconds <= 0){
                jedis.set(realKey, str);
            }else{
                jedis.setex(realKey, seconds, str);
            }
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * Redis数据库 判断键值是否存在
     * @param key
     * @param <T>
     * @return
     */
    public <T>boolean contains(KeyPrefix keyPrefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成数据库真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T>Long incr(KeyPrefix keyPrefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成数据库真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    public <T>Long decr(KeyPrefix keyPrefix, String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成数据库真正的key
            String realKey = keyPrefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    private <T> String beanToString(T value) {
        if (value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class){
            return "" + value;
        } else if(clazz == String.class){
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class){
            return "" + value;
        }else{
            return JSON.toJSONString(value);
        }
    }

    private <T> T stringToBean(String str, Class<T> tClass) {
        if (str == null || str.length() <= 0 || tClass == null)
            return null;
        if (tClass == int.class || tClass == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(tClass == String.class){
            return (T)str;
        }else if(tClass == long.class || tClass == Long.class){
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),tClass);
        }
    }

    /**
     * 将连接归还到连接池
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null){
            jedis.close();
        }
    }
}
