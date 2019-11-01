package com.example.rabbitmq.enums;

import lombok.ToString;

public enum BusinessErrorEnum  {
    //    通用错误100000开头
    PARAM_INVALID(100001,"参数不合法"),//可以动态修改msg内容
    UNKNONW(100002,"未知错误"),

//    200000开头为用户相关错误定义
    USER_NOT_EXIST(200001,"用户不存在"),
    USER_LOGIN_FAIL(200002,"用户账户或密码不正确"),
    USER_NOT_LOGIN(200003,"用户未登陆"),

//    300000开头为交易信息相关错误定义
    STOCK_NOT_ENOUGH(300001,"库存不足"),
    ;

    public  int errCode;
    public String errMsg;

    BusinessErrorEnum(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
