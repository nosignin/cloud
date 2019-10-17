package com.example.quickdevelop.component.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Table {
    //包名
    private String packageName;
    //数据库表名
    private String table_name;
    //类名
    private String tableName;
    //类名首字母小写
    private String tableNameLowercase;
    //类说明
    private String tableComment;
    //数据库字段名，注释，类属性名
    private List<Column> columnList;
    //字段类型去重
    private Set<String> columnTypeSet;

}
