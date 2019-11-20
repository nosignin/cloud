package com.example.redis.config;

import com.tencentyun.TLSSigAPIv2;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class IMConfig {

    @Value("${sdkappid:1400285837}")
    private long sdkappid;
    @Value("${key:9c92b668890493a14db8b9e1ba3a480e5e14bdd2189dfad65ae65bb5520c1960}")
    private String key;
    @Value("${appAdmin:administrator}")
    private String appAdmin;

    @Bean
    public TLSSigAPIv2 tlsSigAPIv2(){
        return new TLSSigAPIv2(sdkappid,key);
    }


}
