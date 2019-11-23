package com.example.redis.aspect;

import com.example.redis.annotation.ExportExcel;
import com.example.redis.entity.CommHttpResult;
import com.example.redis.enums.ResultEnum;
import com.example.redis.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * 将系统生成的对象快速的转换成符合服务器习惯的结果对象
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class WebResultAspect {
    public static final String POINTCUT="execution(* com.example.redis.web.*.*(..))";
    @Pointcut(POINTCUT)
    public void pointCut(){}
    /**
     * 环绕通知
     * 也可以写成 @Around(POINTCUT)
     * @return
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        Object resultData = null;
        Object[] args = joinPoint.getArgs();
        try {
            resultData = joinPoint.proceed(args);
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Annotation[] annotationArray = signature.getMethod().getAnnotations();
            for(Annotation annotation:annotationArray){
                if(annotation instanceof ExportExcel){
                    log.info(">>> 生成excel <<<");
                    return null;
                }
            }
            return new CommHttpResult("success",resultData);
        } catch (Throwable throwable) {
            log.error(">>> {} <<<",throwable.getMessage());
            if(throwable instanceof BaseException){
                return new CommHttpResult("fail",throwable.getMessage());
            }else {
                return new CommHttpResult("fail", ResultEnum.SYSTEM_ERROR.message);
            }
        }
    }
}
