package com.example.springsecurityoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 授权服务器主要是提供用户认证、授权、颁发令牌等功能
 * 一共有四种授权模式，一般用两种
 * 授权码模式
 *      授权码模式有两步
 *      1.请求授权码
 *          get请求：http://localhost:8080/oauth/authorize?client_id=qq&scope=abcdefg&response_type=code&redirect_uri=www.baidu.com
 *          请求发起之后会跳转到一个确认页面，用户需要同意是否进行授权，如果授权那么将会导致页面重定向并得到一个授权码。
 *          在发起这个链接的之前，会需要提供一个用户名密码，通过了才会弹出是否同意的页面，否则会失败
 *      2.请求token
 *          post请求：http://localhost:8080/oauth/token
 *          需要form请求参数：client_id=qq&scope=abcdefg&redirect_uri=www.baidu.com&grant_type=authorization_code&code=xaxsq4
 *          这个请求里面需要加一个Authorization的请求头，内容是Basic cXE6cXFzZWNyZXQ=，这个是将用户名qq和密码qqsecrect进行了base64编码
 *          链接发送完之后会根据第一步得到的授权码得到一个token，
 *          结果：{"access_token":"870bfa3f-d26f-4948-a2bb-be000758e900","token_type":"bearer","refresh_token":"361aecd6-65b3-4ccb-80ac-0dd3789c8f35","expires_in":42566,"scope":"abcdefg"}
 *      之后拿这个access_token就可以去访问资源服务器的资源了
 * 密码模式
 *      密码模式只有一步，因为服务器已经知道有用户名密码，而不是授权码那种可能服务器并没有密码的，需要用授权码交换token。
 *      密码模式一般用于公司内部的服务
 *      请求token
 *          post请求：http://localhost:8080/oauth/token
 *          需要form参数：scope=abcdefg&grant_type=password&username=user&password=123456
 *          结果：{"access_token":"b7c4f9ba-bd25-4093-a0f3-dbaa1d8c1d05","token_type":"bearer","refresh_token":"a03fa3a7-d41d-4ab2-9fc7-038de3a65502","expires_in":43106,"scope":"abcdefg"}
 *      之后拿这个access_token就可以去访问资源服务器的资源了
 */
@Configuration
@EnableResourceServer
public class Oauth2AuthorizationConfig {
}
