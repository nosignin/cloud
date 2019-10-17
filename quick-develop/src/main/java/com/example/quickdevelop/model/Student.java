package com.example.quickdevelop.model;

import lombok.Data;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

/**
* 学生表实体
*/
@Data
public class Student{
    //
    private Long id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别:0-男,1-女
    private Integer gender;

}
