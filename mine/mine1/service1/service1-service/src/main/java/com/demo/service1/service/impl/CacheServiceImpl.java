package com.demo.service1.service.impl;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/04/14:25
 */

import com.demo.service1.entity.User;
import com.demo.service1.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {
    @Cacheable(cacheNames = "service1:cache:users",key="'马'")
    @Override
    public List<User> users() throws InterruptedException {
        log.debug(">>> 未使用缓存，查询速度较慢 <<<");
        Thread.sleep(3000L);
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setName("马云");
        user1.setAge(48);
        User user2 = new User();
        user2.setName("马化腾");
        user2.setAge(38);
        users.add(user1);
        users.add(user2);
        return users;
    }
}
