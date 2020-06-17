package com.example.redis.chain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
// 设置链式编程
@Accessors(chain = true)
public class Student {
    @Excel(name = "id", width = 10)
    private Long id;
    @Excel(name = "name", width = 20)
    private String name;
    @Excel(name = "age", width = 10)
    private Integer age;
}
