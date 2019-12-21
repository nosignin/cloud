package com.example.springsecurityoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 用户从授权服务器获取令牌，
 * 使用获得的令牌到资源服务器申请受保护的用户资源，
 * 资源服务器接收到用户的请求后，会先验证用户的令牌，
 * 只有令牌合法才会把用户请求的资源返回给用户，否则拒绝返回
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ResourceConfig {
}
