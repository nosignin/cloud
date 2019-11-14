package com.example.hystrixclient.web;

import com.example.hystrixclient.service.MyHystrixClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private MyHystrixClient myHystrixClient;

    @RequestMapping(value = "/hystrix/simple")
    public String simpleClientCall(){
        return "rsp: " + myHystrixClient.simpleHystrixClientCall(System.currentTimeMillis());
    }
}
