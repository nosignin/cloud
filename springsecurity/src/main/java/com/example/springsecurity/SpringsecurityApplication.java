package com.example.springsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 参考：https://www.jianshu.com/p/24c6a65c3913 认证流程：源码解读
 */
@SpringBootApplication
@MapperScan(basePackages = "com.example.springsecurity.mapper")
@EnableEurekaClient
public class SpringsecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityApplication.class, args);
	}

}
