package com.example.springsecurityoauth2.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 返回内存中的用户权限信息
     *  get请求：http://localhost:8080/user/me
     *  带上请求头Authorization，内容是刚刚的access_token，注意需要在前面加上bearer ：bearer f303a080-77df-4659-a7b8-d89cc76ed035
     *  得到结果：{"password":null,"username":"user","authorities":[{"authority":"ROLE_ACTUATOR"},{"authority":"ROLE_USER"}],"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}
     * @param userDetails
     * @return
     */
    @GetMapping("/me")
    public Object me(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
