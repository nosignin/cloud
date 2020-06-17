package com.example.redis.web;

import com.alibaba.fastjson.JSONObject;
import com.example.redis.service.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im")
public class IMController {
    @Autowired
    private IMService imService;


    /**
     * 查询用户状态
     * 链接 localhost:51673/im/query/state 参数 {"To_Account":["a26002"]}
     * 结果：{"status":"success","data":{"QueryResult":[{"To_Account":"@TLS#NOT_FOUND","State":"Offline"}],"ErrorInfo":"","ErrorCode":0,"ActionStatus":"OK"}}
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/query/state",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object querystate(@RequestBody JSONObject jsonObject){
        Object querystate = imService.querystate(jsonObject);
        return querystate;
    }
}
