package com.example.redis.annotation;

import com.example.redis.enums.ExcelEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExportExcel {
    ExcelEnum excelEnum() default ExcelEnum.DEFAULT;
    Class exportClass();//必须指定Class
}
