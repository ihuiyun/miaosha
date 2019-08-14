package com.zld.mall.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 10:05
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private String host;
    private String password;
    private int port;
    private int database;
    private int timeout;
    private Jedis jedis;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public static class Jedis{
        private Pool pool;

        public Pool getPool() {
            return pool;
        }

        public void setPool(Pool pool) {
            this.pool = pool;
        }

        public static class Pool{
            private int max_active;
            private int max_idle;
            private int max_wait;
            public int getMax_active() {
                return max_active;
            }

            public void setMax_active(int max_active) {
                this.max_active = max_active;
            }

            public int getMax_idle() {
                return max_idle;
            }

            public void setMax_idle(int max_idle) {
                this.max_idle = max_idle;
            }

            public int getMax_wait() {
                return max_wait;
            }

            public void setMax_wait(int max_wait) {
                this.max_wait = max_wait;
            }
        }
    }
}
