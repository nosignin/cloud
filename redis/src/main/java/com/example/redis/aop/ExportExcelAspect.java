package com.example.redis.aop;

import com.example.redis.annotation.ExportExcel;
import com.example.redis.enums.ExcelEnum;
import com.example.redis.util.EasyPoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Aspect
@Component
@Slf4j
public class ExportExcelAspect {
    @Pointcut("@annotation(com.example.redis.annotation.ExportExcel)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        Object result = joinPoint.proceed();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ExportExcel annotation = signature.getMethod().getAnnotation(ExportExcel.class);
        ExcelEnum excelEnum = annotation.excelEnum();
        Class clazz = annotation.exportClass();
        List list = (List) result;
        long timeStamp = System.currentTimeMillis();
        EasyPoiUtils.exportExcel(list, excelEnum.title, excelEnum.sheetName, clazz, excelEnum.fileName+"_" + timeStamp + ".xls", response);
        return result;
    }

}
