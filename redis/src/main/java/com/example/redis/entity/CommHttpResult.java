package com.example.redis.entity;

import lombok.Data;

/**
 * 公共返回类
 */
@Data
public class CommHttpResult {
    private String status;//success,fail
    private Object data;

    public CommHttpResult(String status, Object data){
        this.setStatus(status);
        this.setData(data);
    }
}
