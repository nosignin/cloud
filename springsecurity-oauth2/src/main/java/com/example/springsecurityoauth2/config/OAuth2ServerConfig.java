package com.example.springsecurityoauth2.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
* @Description: oauth2的资源服务器和授权服务器的配置
 * 参考：http://blog.didispace.com/spring-security-oauth2-xjf-1/
 * https://github.com/lexburner/oauth2-demo
 * https://blog.csdn.net/AaronSimon/article/details/83546827
 * https://github.com/simondongji/SpringCloudProject
* @Param:
* @return:
* @Author: 石佳
* @Date: 2020/4/17
*/
@Configuration
public class OAuth2ServerConfig {

    private static final String DEMO_RESOURCE_ID = "order";

    /**
    * @Description: 用户从授权服务器获取令牌，
     * 使用获得的令牌到资源服务器申请受保护的用户资源，
     * 资源服务器接收到用户的请求后，会先验证用户的令牌，
     * 只有令牌合法才会把用户请求的资源返回给用户，否则拒绝返回
    * @Param: 
    * @return: 
    * @Author: 石佳
    * @Date: 2020/4/17
    */
//    @Configuration
//    @EnableResourceServer
//    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) {
//            resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http
//                .authorizeRequests()
//                    .antMatchers("/order/**").authenticated();//配置order访问控制，必须认证过后才可以访问
//
//        }
//    }

    /**
    * @Description: 授权服务器主要是提供用户认证、授权、颁发令牌等功能
     * 请求token
     * 	都是post请求到http://localhost:8080/oauth/token，但是参数需要在body里面通过form表单的方式提交
     * 	客户端模式
     * 		参数：grant_type=client_credentials& scope=select& client_id=client_1& client_secret=123456
     * 	密码模式
     * 		参数：username=user_1&password=123456& grant_type=password&scope=select& client_id=client_2&client_secret=123456
     * 	授权码模式
     * 	    先登录，post请求到http://localhost:8080/login，form表单提交，参数是username=user_1&password=123456
     * 	    登录完会触发302跳转到http://localhost:8080/oauth/authorize?response_type=code&client_id=client1&redirect_uri=http://baidu.com
     * 	    在页面选择允许，确认后触发302跳转到https://www.baidu.com/?code=pyhAqn
     * 	    得到了code之后post请求到http://localhost:8080/oauth/token，form表单提交，参数是grant_type=authorization_code&client_id=client1&client_secret=123456&code=pyhAqn&redirect_uri=http://baidu.com
     * 	    得到了我们的token：{"access_token":"6d6bed5f-d6ca-4b65-959c-10105a42f05f","token_type":"bearer","refresh_token":"2ecc242d-a753-4ffa-98ea-a80b22aa3196","expires_in":43199,"scope":"test"}
     * 访问资源
     * 	http://localhost:8080/order/1?access_token=2a62d637-38dc-4d56-8ef6-da80b515b5cb
    * @Param: 
    * @return: 
    * @Author: 石佳
    * @Date: 2020/4/17
    */
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        RedisConnectionFactory redisConnectionFactory;


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

//        password 方案一：明文存储，用于测试，不能用于生产
//        String finalSecret = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalSecret = new BCryptPasswordEncoder().encode("123456");
            // password 方案三：支持多种编码，通过密码的前缀区分编码方式
            String finalSecret = "{bcrypt}"+new BCryptPasswordEncoder().encode("123456");
            //配置两个客户端,一个用于password认证一个用于client认证
            clients.inMemory().withClient("client_1")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("client_credentials", "refresh_token")
                    .scopes("select")
                    .authorities("oauth2")
                    .secret(finalSecret)
                    .and().withClient("client_2")
                    .resourceIds(DEMO_RESOURCE_ID)
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("select")
                    .authorities("oauth2")
                    .secret(finalSecret)
                    .and().withClient("client1")//用于标识用户ID
                    .authorizedGrantTypes("authorization_code","client_credentials","password","implicit","refresh_token")//授权方式
                    .scopes("test")//授权范围
                    .secret(finalSecret)
                    .redirectUris("http://baidu.com");
        }

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
            endpoints
                    .authenticationManager(authenticationManager)
                    .tokenStore(new RedisTokenStore(redisConnectionFactory))
                    .userDetailsService(userDetailsService)
                    .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
            //允许表单认证
            oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").allowFormAuthenticationForClients();
        }

    }

}
