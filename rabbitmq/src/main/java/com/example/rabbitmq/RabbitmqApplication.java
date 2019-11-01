package com.example.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO 搭建redis项目
// TODO 搭建zuul项目,丰富ZuulFilter内容,参考:https://blog.csdn.net/weixin_38003389/article/details/83650456
// TODO 搭建elastic-job项目
@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

}
