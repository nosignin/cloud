package com.example.quickdevelop.component.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException{

	private int code;
	private String msg;

	public CustomException(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public static CustomException error(String msg){
		return new CustomException(500,msg);
	}

}
