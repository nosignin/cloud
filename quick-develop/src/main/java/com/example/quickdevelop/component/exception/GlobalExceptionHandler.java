package com.example.quickdevelop.component.exception;

import com.example.quickdevelop.component.common.Result;
import com.example.quickdevelop.component.constant.ResultConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Exception异常处理
	 * 优先级最低，如果没有走其他通道则默认走此通道
	 * 可在此判断异常类型，响应json
	 */
	@ResponseBody
	@ExceptionHandler(value={Exception.class})
	public Object exceptionHandler(Exception e){
		log.info("===exception异常处理===");
		e.printStackTrace();
		if(e instanceof HttpRequestMethodNotSupportedException){
			return ResultConstant.SYSTEM.REQUEST_METHOD_EXCEPTION_RESULT;
		}
		else{
			return ResultConstant.SYSTEM.SYSTEM_EXCEPTION_RESULT;
		}
	}

	/**
	 * CustomException自定义异常处理
	 */
	@ResponseBody
	@ExceptionHandler(value={CustomException.class})
	public Object customExceptionHandler(CustomException e){
		log.info("===CustomException异常处理===");
		e.printStackTrace();
		return new Result(e.getCode(),e.getMsg());
	}

	/**
	 * AccessDeniedException
	 */
	@ResponseBody
	@ExceptionHandler(value={AccessDeniedException.class})
	public Object accessDeniedExceptionHandler(){
		return ResultConstant.SYSTEM.ACCESS_DENIED_EXCEPTION_RESULT;
	}

	/**
	 * HttpMessageNotReadableException
	 */
	@ResponseBody
	@ExceptionHandler(value={HttpMessageNotReadableException.class})
	public Object httpMessageNotReadableExceptionHandler(){
		return ResultConstant.SYSTEM.HTTP_MESSAGE_NOT_READABLE_EXCEPTION_RESULT;
	}


}
