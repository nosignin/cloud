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
                .antMatchers("/test/**")  // 当设置这个页面不拦截时，可以直接访问/test/hello
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN") //用hasRole时，在我们返回的UserDetails的Authority需要加Role_ADMIN
                .antMatchers("/read/**")
                .hasAuthority("read") //用户自定义的权限，返回的UserDetails的Authority只要与这里匹配就可以，这里不需要加ROLE_
//                .access("hasRole('ADMIN') and hasIpAddress('192.168.0.1')") //指定有ADMIN权限并且匹配相应的IP
                .antMatchers("/god/**")
                .access("@UserAccessService.pass(request,authentication)") //使用自己自定义的方法控制权限
                .anyRequest()
                .authenticated() // 设置除自己和系统配置之外，其他的页面需要授权。
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
