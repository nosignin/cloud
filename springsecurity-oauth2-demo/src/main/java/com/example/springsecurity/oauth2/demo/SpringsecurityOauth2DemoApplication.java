package com.example.springsecurity.oauth2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class SpringsecurityOauth2DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityOauth2DemoApplication.class, args);
    }

    @GetMapping("/1")
    public String getUser() {
        return "hello";
    }
}
