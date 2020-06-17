package com.example.redis.web;

import com.example.redis.service.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
@Slf4j
public class TestRedissonController {
    @Autowired
    private RedissonService redissonService;

    /**
     * 设置等待锁5秒钟，6秒钟后消失。如果没有获取到锁，直接报错
     * 如果key一直存在，直接就报错
     * @param id
     * @return
     */
    @PostMapping("/test")
    public Object test(String id){
        RLock rLock = redissonService.getRLock(id);
        try {
            boolean isGetLock = rLock.tryLock(5, 6, TimeUnit.SECONDS);
            if(isGetLock){
                log.info(">>> 获取到锁：{} <<<",id);
            }else{
                log.info(">>> 未获取到锁： {} <<<",id);
                Thread.sleep(300);
            }
        }catch (Exception e){
            log.error(">>> 发现异常： {} <<<",e);
        }finally {
            rLock.unlock();
        }
        return null;
    }
}
