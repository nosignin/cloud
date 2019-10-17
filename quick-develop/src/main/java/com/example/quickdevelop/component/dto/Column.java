package com.example.quickdevelop.component.dto;

import lombok.Data;

@Data
public class Column {
    //数据库字段名
    private String column_name;
    //类属性名称
    private String columnName;
    //数据库字段类型
    private String column_type;
    //类属性类型
    private String columnType;
    //注释说明
    private String columnComment;

}
