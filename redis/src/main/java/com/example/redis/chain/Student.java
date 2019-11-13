package com.example.redis.chain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
// 设置链式编程
@Accessors(chain = true)
public class Student {
    private Long id;
    private String name;
    private Integer age;
}
