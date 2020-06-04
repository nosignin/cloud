package com.mysql.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

/**
 * 参考：https://mp.weixin.qq.com/s/73_8_O5qMkn-gcJH2IZbJg
* @author 石佳
* @since 2020/5/12
*/
@SpringBootApplication(exclude={JpaRepositoriesAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
