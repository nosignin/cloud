package com.example.mybatis.plus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 石佳
 * @Date: 2020/04/07/9:42
 * @Description: mybatisPlus 配置类，使其加载配置文件
 */
@Configuration
@ImportResource(locations = {"classpath:/mybatis/spring-mybatis.xml"})
public class MybatisPlusConfig {
}
