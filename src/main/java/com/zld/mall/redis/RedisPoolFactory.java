package com.zld.mall.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 描述：RedisPoolFactory
 *
 * @author lida
 * @time 2019/8/14 11:32
 */
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;
    /**
     * 生成一个JedisPool的对象
     * @return JedisPool
     */
    @Bean(name = "jedisPool")
    public JedisPool getJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getJedis().getPool().getMax_idle());
        jedisPoolConfig.setMaxTotal(redisConfig.getJedis().getPool().getMax_active());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getJedis().getPool().getMax_wait() * 1000);
        JedisPool jd = new JedisPool(jedisPoolConfig, redisConfig.getHost(),redisConfig.getPort(),
                redisConfig.getTimeout()*1000,redisConfig.getPassword(),redisConfig.getDatabase());
        return jd;
    }
}
