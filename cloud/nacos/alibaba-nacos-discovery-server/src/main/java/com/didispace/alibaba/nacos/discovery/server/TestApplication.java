package com.didispace.alibaba.nacos.discovery.server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
*  参考：http://blog.didispace.com/spring-cloud-alibaba-1/
 *  先启动nacos,然后进入控制台，http://127.0.0.1:8848/nacos
 *  启动server的两个实例，后common，然后访问http://localhost:9000/test，会随机轮询
* @author 石佳
* @since 2020/5/8
*/
@EnableDiscoveryClient
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.didispace.alibaba.nacos.discovery.server.TestApplication.class, args);
    }

    @Slf4j
    @RestController
    static class TestController {

        @GetMapping("/hello")
        public String hello(@RequestParam String name) {
            log.info("invoked name = " + name);
            return "hello " + name;
        }

    }

}