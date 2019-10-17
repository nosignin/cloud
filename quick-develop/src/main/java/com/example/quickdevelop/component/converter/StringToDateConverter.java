package com.example.quickdevelop.component.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间转换器
 */
@Component
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String timeStr) {
        try {
            if (timeStr != null && timeStr.length() == 10) {
                return new SimpleDateFormat("yyyy-MM-dd").parse(timeStr);
            } else if (timeStr != null && timeStr.length() == 19) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
