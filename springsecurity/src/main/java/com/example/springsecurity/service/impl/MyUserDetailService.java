package com.example.springsecurity.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * springsecurity框架核心组件:
 * SecurityContextHolder：提供对SecurityContext的访问
 * SecurityContext,：持有Authentication对象和其他可能需要的信息
 * AuthenticationManager 其中可以包含多个AuthenticationProvider
 * ProviderManager对象为AuthenticationManager接口的实现类
 * AuthenticationProvider 主要用来进行认证操作的类 调用其中的authenticate()方法去进行认证操作
 * Authentication：Spring Security方式的认证主体
 * GrantedAuthority：对认证主题的应用层面的授权，含当前用户的权限信息，通常使用角色表示
 * UserDetails：构建Authentication对象必须的信息，可以自定义，可能需要访问DB得到
 * UserDetailsService：通过username构建UserDetails对象，通过loadUserByUsername
 * 根据userName获取UserDetail对象 （可以在这里基于自身业务进行自定义的实现  如通过数据库，xml,缓存获取等）
 */
@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {// UserDetailsService 我的理解是用户关系的来源，可以自定义数据源
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 在当前的业务中，用户所有的密码都被设置成123456，访问其他页面后会自动跳转到登录页面，
        // 任意输入一个用户名比如aaa，输入密码123456，即可正确登录，然后跳转到之前想进入的页面
        return new User(username,passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
    }
}
