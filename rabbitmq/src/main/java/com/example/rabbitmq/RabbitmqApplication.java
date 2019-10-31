package com.example.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO 完善RabbitMessage传参校验BindingResult:非空;
// TODO 完善全局异常捕获内容,全局日志追踪
// TODO 完善swagger-ui整合
// TODO 完善mq使用方法,参考:https://www.jianshu.com/p/c0cba2546900
// TODO 搭建redis项目
// TODO 搭建zuul项目,丰富ZuulFilter内容,参考:https://blog.csdn.net/weixin_38003389/article/details/83650456
@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

}
