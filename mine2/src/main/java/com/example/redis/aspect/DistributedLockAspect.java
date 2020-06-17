package com.example.redis.aspect;

import com.example.redis.annotation.DistributedLock;
import com.example.redis.service.DistributedLockCallback;
import com.example.redis.service.DistributedLockTemplate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DistributedLockAspect {
    @Autowired
    private DistributedLockTemplate lockTemplate;

    @Pointcut("@annotation(com.example.redis.annotation.DistributedLock)")
    public void aspect(){}

    /**
     *
     * @param joinPoint
     * @param distributedLock 优化代码，快速得到注解
     * @return
     */
    @Around("DistributedLockAspect.aspect() && @annotation(distributedLock)")
    public Object doAround(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) throws Throwable {
        String lockName = distributedLock.lockName();
        boolean fairLock = distributedLock.fairLock();
        boolean tryLock = distributedLock.tryLock();
        if(tryLock){
            return tryLock(joinPoint, lockName, fairLock);
        }else {
            return getLock(joinPoint, lockName, fairLock);
        }
    }

    private Object getLock(ProceedingJoinPoint joinPoint, String lockName, boolean fairLock) throws Throwable {
        return lockTemplate.lock(new DistributedLockCallback<Object>() {
            @Override
            public Object process() throws Throwable {
                return joinPoint.proceed();
            }

            @Override
            public String getLockName() {
                return lockName;
            }
        },fairLock);
    }

    private Object tryLock(ProceedingJoinPoint joinPoint, String lockName, boolean fairLock) throws Throwable {
        return lockTemplate.tryLock(new DistributedLockCallback<Object>() {
            @Override
            public Object process() throws Throwable {
                return joinPoint.proceed();
            }

            @Override
            public String getLockName() {
                return lockName;
            }
        },fairLock);
    }
}
