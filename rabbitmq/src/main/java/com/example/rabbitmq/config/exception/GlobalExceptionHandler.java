package com.example.rabbitmq.config.exception;

import com.example.rabbitmq.entity.CommHttpResult;
import com.example.rabbitmq.entity.ErrorResult;
import com.example.rabbitmq.enums.BusinessErrorEnum;
import com.example.rabbitmq.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring 3.2 中，新增了 @ControllerAdvice 注解，
 * 可以用于定义 @ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有 @RequestMapping 中。
 * 简单来说就是，可以通过 @ControllerAdvice 注解配置一个全局异常处理类，来统一处理 controller 层中的异常，
 * 于此同时 controller 中可以不用再写 try/catch，这使得代码既整洁又便于维护。
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 系统异常
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommHttpResult exceptionHandler(HttpServletRequest httpServletRequest,Exception e){
        log.error(">>> 服务错误:{} <<<",e);
        return new  CommHttpResult("false",new ErrorResult(BusinessErrorEnum.UNKNONW));
    }

    /**
     * 业务异常
     * @param httpServletRequest
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public CommHttpResult businessExceptionHandler(HttpServletRequest httpServletRequest,BusinessException e){
        log.error(">>> 业务异常.code:{},msg:{} <<<",e.getBusinessErrorEnum().errCode,e.getBusinessErrorEnum().errMsg);
        return  new CommHttpResult("false", new ErrorResult(e.getBusinessErrorEnum()));
    }


}
