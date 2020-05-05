package com.demo.service1.controller;

import com.demo.service1.entity.User;
import com.demo.service1.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/04/14:16
 */
@RestController
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private CacheService cacheService;

    /**
    *  第一次查询会比较慢，debug信息看到是未经过缓存的，第二次就非常快了，因为走了缓存
    * @return 用户列表
    * @author 石佳
    * @since 2020/5/4
    */
    @GetMapping("/cacheable")
    public List<User> users() throws InterruptedException {
        return cacheService.users();
    }
}
