package com.example.springsecurity.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
@Slf4j
public class ReadController {
    @GetMapping("/hello")
    public String hello(){
        log.info(">>> hello <<<");
        return "hello";
    }
}
