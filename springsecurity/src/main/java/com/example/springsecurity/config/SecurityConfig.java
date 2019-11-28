package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * WebSecurityConfigurerAdapter 应该相当于security框架的总开关/入口，配置了这个拦截器之后，
 * springsecurity框架就开始作用，对指定的链接进行处理
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        我的理解是authorizeRequests()相当于告诉security框架我要开始指定拦截规则了，
//        后面指定自己的拦截规则。如果这里不写，默认是放行的，不会有任何拦截
        http.authorizeRequests()
                .antMatchers("/test/hello")  // 当设置这个页面不拦截时，可以直接访问/test/hello，其他的则会禁止未授权访问
                .permitAll()
                .anyRequest()
                .authenticated() // 设置其他的页面需要授权，但是登录页和其他的几个？必须的页面另外
                .and()
                .formLogin(); //指定表单登录
        http.csrf().disable();  // 禁用csrf
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//密码加密器
    }
}
