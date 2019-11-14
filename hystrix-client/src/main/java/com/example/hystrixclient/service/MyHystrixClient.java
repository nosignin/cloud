package com.example.hystrixclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class MyHystrixClient {
    @Resource
    private RestTemplate restTemplate;

//    @HystrixCommand(fallbackMethod = "myFallback")
    public String simpleHystrixClientCall(long time){
        return restTemplate.getForEntity("http://HYSTRIX-SERVER/hystrix/simple?time="+time,String.class).getBody();
    }

    /**
     * 方法simpleHystrixClientCall的回退方法，可以指定将hystrix执行失败异常传入到方法中
     * @return
     */
//    String myFallback(long p,Throwable e){
//        return "Execute raw fallback: access service fail , req= " + p + " reason = " + e;
//    }
}
