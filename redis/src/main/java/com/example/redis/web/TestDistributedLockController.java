package com.example.redis.web;

import com.example.redis.annotation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/distribute/lock")
@Slf4j
public class TestDistributedLockController {
    @PostMapping("/default")
    @DistributedLock
    public Object defaultLock(){
        log.info(">>> defaultLock <<<");
        return System.currentTimeMillis();
    }

    @PostMapping("/lockname")
    @DistributedLock(lockName = "mylock")
    public void lockname(){
        log.info(">>> lockname <<<");
    }

    @PostMapping("/tryLock")
    @DistributedLock(tryLock = true)
    public void tryLock(){
        log.info(">>> tryLock <<<");
    }
}
