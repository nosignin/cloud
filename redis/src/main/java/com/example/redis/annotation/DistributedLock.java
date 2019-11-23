package com.example.redis.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {
    /**
     * 锁的名称。
     * 如果lockName可以确定，直接设置该属性。
     * @return
     */
    String lockName() default "defaultLockName";

    /**
     * 是否使用公平锁。
     * @return
     */
    boolean fairLock() default false;

    /**
     * 是否使用尝试锁。
     * @return
     */
    boolean tryLock() default false;

    /**
     * 最长等待时间。
     * 该字段只有当tryLock()返回true才有效。
     * @return
     */
    long waitTime() default 30L;

    /**
     * 锁超时时间。
     * 超时时间过后，锁自动释放。
     * @return
     */
    long leaseTime() default 5L;

    /**
     * 时间单位。默认为秒。
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
