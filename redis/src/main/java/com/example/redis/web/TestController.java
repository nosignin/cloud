package com.example.redis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 如果第一次设置,就设置成功,保留1分钟.
     * 如果第二次设置,就返回失败
     * 用postman访问:localhost:8080/test/redis/set
     * @param key
     * @param value
     * @return
     */
    @PostMapping("/redis/set")
    public Object setKey(String key,String value){
        Boolean aBoolean = redisTemplate.opsForValue().setIfAbsent(key, value);
        if(aBoolean){
            redisTemplate.expire(key,1L, TimeUnit.MINUTES);
        }
        return "设置数据:"+(aBoolean.equals(true)?"成功":"失败");
    }
}
