package com.demo.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 石佳
 * @since 2020/06/19
 */
@Slf4j
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;
// 连接超时时间（毫秒）
    @Value("${spring.redis.timeout:10000}")
    private int timeout;
// 连接池中的最大空闲连接
    @Value("${spring.redis.jedis.pool.max-idle:200}")
    private int maxIdle;
//    连接池最大阻塞等待时间（使用负值表示没有限制）
    @Value("${spring.redis.jedis.pool.max-wait:10000}")
    private long maxWaitMillis;

    @Value("${spring.redis.password:}")
    private String password;

    @Value("${spring.redis.block-when-exhausted:true}")
    private boolean  blockWhenExhausted;

    @Bean
    public JedisPool redisPoolFactory()  throws Exception{
        log.info(">>> JedisPool注入成功！！ <<<");
        log.info(">>> redis地址：{}:{} <<<" , host ,port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = null;
        if(StringUtils.isEmpty(password)){
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
        }else{
            jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        }
        return jedisPool;
    }
}
