package com.example.redis.service;

import org.redisson.api.*;

import java.io.IOException;

public interface RedissonService {
    public <T> RBucket<T> getRBucket(String objectName) throws IOException;
    public <K,V> RMap<K,V> getMap(String objectName);
    public <V> RSortedSet<V> getRSortSet(String objectName);
    public <V> RSet<V> getRSet(String objectName);
    public <V> RList<V> getRList(String objectName);
    public <V> RQueue<V> getRQueue(String objectName);
    public <V> RDeque<V> getRDeque(String objectName);
    public RLock getRLock(String objectName);
    public RLock getRLock(String objectName,boolean fairLock);
    public RReadWriteLock getRWLock(String objectName);
    public RAtomicLong getRAtomicLong(String objectName);
    public RCountDownLatch getRCountDownLatch(String objectName);
}
