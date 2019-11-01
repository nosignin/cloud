package com.example.zuulclient.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * 访问localhost:5000/hello-service/test/zuul可以得到结果:from zuul
     * @return
     */
    @PostMapping("/zuul")
    public Object testZuul(){
        return "from zuul";
    }
}
