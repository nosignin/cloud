package com.demo.service1.service;


import com.demo.service1.entity.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author 石佳
 * @since 2020/05/04/14:25
 */
public interface CacheService {
    List<User> users() throws InterruptedException;
}
