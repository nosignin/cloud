package com.example.redis.entity;

import lombok.Data;

@Data
public class Dictionary {
    Integer id;
//    字典的key
    String key;
//    字典的值
    String value;
//    是否激活:0-激活,1-未激活
    Integer status;
//    描述
    String description;
}
