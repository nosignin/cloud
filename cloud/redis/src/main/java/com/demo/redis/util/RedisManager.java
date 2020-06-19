package com.demo.redis.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author 石佳
 * @since 2020/06/19
 */
@Component
public class RedisManager {
    @Resource
    private JedisPool jedisPool;

    private Jedis getJedis(){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        }catch (RuntimeException e){
            if(jedis != null ) {
                jedisPool.close();//获取连接失败时，应该返回给pool,否则每次发生异常将导致一个jedis对象没有被回收。
            }
        }
        return jedis;
    }

    /**
    *  尝试获取分布式锁
    * @param key 锁
    * @param requestId 请求标识
    * @return boolean 加锁结果
    * @author 石佳
    * @since 2020/6/19
    */
    public boolean lockKey(String key,String requestId){
        Jedis jedis = getJedis();
        boolean locked = RedisTool.tryGetDistributedLock(jedis, key, requestId, 1500);
        return locked;
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseKey(String lockKey, String requestId){
        Jedis jedis = getJedis();
        boolean locked = RedisTool.releaseDistributedLock(jedis, lockKey, requestId);
        return locked;
    }
}
