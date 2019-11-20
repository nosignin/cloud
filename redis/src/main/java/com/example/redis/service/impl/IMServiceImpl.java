package com.example.redis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.redis.config.IMConfig;
import com.example.redis.service.IMService;
import com.tencentyun.TLSSigAPIv2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class IMServiceImpl implements IMService {
    @Autowired
    private TLSSigAPIv2 tlsSigAPIv2;
    @Autowired
    private IMConfig imConfig;
    @Resource
    private RestTemplate restTemplate;

    public Object querystate(JSONObject jsonObject){
        String url = "https://console.tim.qq.com/v4/openim/querystate";
        url = packageIMUrl(url);
        JSONObject postData = new JSONObject();
        postData.put("To_Account",jsonObject.get("To_Account"));
        ResponseEntity<JSONObject> jsonObjectResponseEntity = restTemplate.postForEntity(url, postData, JSONObject.class);
        JSONObject body = jsonObjectResponseEntity.getBody();
        return body;
    }

    /**
     * 将url封装成im的通用url，加上默认的字段
     * @param url
     * @return
     */
    private String packageIMUrl(String url) {
        String identifier = imConfig.getAppAdmin();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        stringBuilder.append("?");
        stringBuilder.append("identifier="+identifier);
        stringBuilder.append("&");
        stringBuilder.append("random="+ RandomStringUtils.randomAlphabetic(5));
        stringBuilder.append("&");
        long sdkappid = imConfig.getSdkappid();
        stringBuilder.append("sdkappid="+ sdkappid);
        stringBuilder.append("&");
        String usersig = tlsSigAPIv2.genSig(identifier, 100 * 3600 * 24);
        stringBuilder.append("usersig="+ usersig);
        stringBuilder.append("&");
        String contenttype = "json";
        stringBuilder.append("contenttype="+ contenttype);
        url = stringBuilder.toString();
        return url;
    }
}
