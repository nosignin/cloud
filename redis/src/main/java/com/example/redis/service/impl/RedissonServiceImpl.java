package com.example.redis.service.impl;

import com.example.redis.service.RedissonService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class RedissonServiceImpl implements RedissonService {
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 获取字符串对象
     * @param objectName
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> RBucket<T> getRBucket(String objectName) throws IOException {
        RBucket<T> bucket = redissonClient.getBucket(objectName);
        return bucket;
    }

    /**
     * 获取map对象
     * @param objectName
     * @param <K>
     * @param <V>
     * @return
     */
    public <K,V> RMap<K,V> getMap(String objectName){
        RMap<K, V> map = redissonClient.getMap(objectName);
        return map;
    }

    /**
     * 获取有序集合
     * @param objectName
     * @param <V>
     * @return
     */
    public <V> RSortedSet<V> getRSortSet(String objectName){
        RSortedSet<V> sortedSet = redissonClient.getSortedSet(objectName);
        return sortedSet;
    }

    /**
     * 获取集合
     * @param objectName
     * @param <V>
     * @return
     */
    public <V>RSet<V> getRSet(String objectName){
        RSet<V> set = redissonClient.getSet(objectName);
        return set;
    }

    /**
     * 获取列表
     * @param objectName
     * @param <V>
     * @return
     */
    public <V> RList<V> getRList(String objectName){
        RList<V> list = redissonClient.getList(objectName);
        return list;
    }

    /**
     * 获取队列
     * @param objectName
     * @param <V>
     * @return
     */
    public <V> RQueue<V> getRQueue(String objectName){
        RQueue<V> queue = redissonClient.getQueue(objectName);
        return queue;
    }

    /**
     * 获取双端队列
     * @param objectName
     * @param <V>
     * @return
     */
    public <V> RDeque<V> getRDeque(String objectName){
        RDeque<V> rDeque = redissonClient.getDeque(objectName);
        return rDeque;
    }

    /**
     * 获取锁
     * @param objectName
     * @return
     */
    public RLock getRLock(String objectName){
        return getRLock(objectName,false);
    }

    /**
     * 获取锁
     * @param objectName
     * @param fairLock 是否指定公平锁
     * @return
     */
    @Override
    public RLock getRLock(String objectName, boolean fairLock) {
        RLock lock;
        if(fairLock){
            lock = redissonClient.getFairLock(objectName);
        }else {
            lock = redissonClient.getLock(objectName);
        }
        return lock;
    }

    /**
     * 获取读取锁
     * @param objectName
     * @return
     */
    public RReadWriteLock getRWLock(String objectName){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(objectName);
        return readWriteLock;
    }

    /**
     * 获取原子数
     * @param objectName
     * @return
     */
    public RAtomicLong getRAtomicLong(String objectName){
        RAtomicLong atomicLong = redissonClient.getAtomicLong(objectName);
        return atomicLong;
    }

    /**
     * 获取记数锁
     * @param objectName
     * @return
     */
    public RCountDownLatch getRCountDownLatch(String objectName){
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(objectName);
        return countDownLatch;
    }


}
