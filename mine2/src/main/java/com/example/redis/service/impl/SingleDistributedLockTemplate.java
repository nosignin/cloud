package com.example.redis.service.impl;

import com.example.redis.service.DistributedLockCallback;
import com.example.redis.service.DistributedLockTemplate;
import com.example.redis.service.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SingleDistributedLockTemplate implements DistributedLockTemplate {
    @Autowired
    private RedissonService redissonService;

    @Override
    public <T> T lock(DistributedLockCallback<T> callback, boolean fairLock) throws Throwable {
        return lock(callback,DEFAULT_TIMEOUT,DEFAULT_TIME_UNIT,fairLock);
    }

    @Override
    public <T> T lock(DistributedLockCallback<T> callback, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws Throwable {
        RLock lock = redissonService.getRLock(callback.getLockName(), fairLock);
        try {
            lock.lock(leaseTime,timeUnit);
            return callback.process();
        }finally {
            if(lock != null && lock.isLocked()){
                lock.unlock();
            }
        }
    }

    @Override
    public <T> T tryLock(DistributedLockCallback<T> callback, boolean fairLock) throws Throwable {
        return tryLock(callback,DEFAULT_WAIT_TIME,DEFAULT_TIMEOUT,DEFAULT_TIME_UNIT,fairLock);
    }

    @Override
    public <T> T tryLock(DistributedLockCallback<T> callback, long waitTime, long leaseTime, TimeUnit timeUnit, boolean fairLock) throws Throwable {
        String lockName = callback.getLockName();
        RLock lock = redissonService.getRLock(lockName, fairLock);
        boolean getLock = false;
        try {
            getLock = lock.tryLock(waitTime, leaseTime, timeUnit);
            if(getLock){
                return callback.process();
            }
        } catch (InterruptedException e) {
            log.error(">>> 加锁出现异常：{} <<<", lockName);
        }finally {
            if(getLock && lock!=null  && lock.isLocked()){
                lock.unlock();
            }
        }
        return null;
    }
}
