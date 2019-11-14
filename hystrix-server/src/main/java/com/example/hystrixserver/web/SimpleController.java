package com.example.hystrixserver.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SimpleController {
    private AtomicInteger count = new AtomicInteger();
    private AtomicInteger sleepCount = new AtomicInteger();

    @GetMapping(value="/hystrix/simple")
    public String hystrixClientCall(@RequestParam("time") long time){
        int newCount = count.incrementAndGet();
        return "time " + time + " hystrix" + newCount + ": " + ThreadLocalRandom.current().nextInt(1000);
    }
}
