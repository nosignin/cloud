package com.example.hystrixserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * Hystrix的配置属性优先级和详解 参考:https://blog.csdn.net/hry2015/article/details/78554846
 */
@SpringBootApplication
@EnableEurekaClient
public class HystrixServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixServerApplication.class, args);
	}

}
