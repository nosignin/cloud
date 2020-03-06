package com.example.zipkin.a;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class ZipkinAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinAApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String callHome(){
        log.info("calling trace zipkin-a");
        return restTemplate.getForObject("http://localhost:11004/hi",String.class);
    }

    @RequestMapping("/info")
    public String info(){
        log.info("calling trace zipkin-a");
        return "i am zipkin-a";
    }
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}
