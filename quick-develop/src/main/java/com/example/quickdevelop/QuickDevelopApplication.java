package com.example.quickdevelop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 通过逆向工程快速开发,表设计完成后里面快速生成常用增删改查代码
 * 执行前先运行test.sql,然后运行GeneratorQuickDevelop,生成文件后用SnowflakeIdUtil替换掉雪花算法,解决依赖即可
 */
@SpringBootApplication
@EnableEurekaClient
public class QuickDevelopApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickDevelopApplication.class, args);
	}

}
