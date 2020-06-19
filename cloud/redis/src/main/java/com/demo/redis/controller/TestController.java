package com.demo.redis.controller;

import com.demo.redis.util.RedisManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 石佳
 * @since 2020/06/19
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private RedisManager redisManager;

    @GetMapping("/test1")
    public String test1(){
        boolean locked = redisManager.lockKey("test1", UUID.randomUUID().toString());
        if(locked){
            return "加锁成功";
        }
        return "加锁失败";
    }
}
