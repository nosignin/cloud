package com.example.redis.enums;

public enum ResultEnum {
    SYSTEM_ERROR(10001,"系统发生错误"),

//    业务类型的返回枚举
    RECOMMEND_STOCK_RISE_MUST_NOT_BELOW_MIN_RISE(20001,"荐股预期涨幅不能低于最小预期涨幅"),
    RECOMMEND_STOCK_RISE_MUST_NOT_UPPER_MAX_RISE(20002,"荐股预期涨幅不能高于最大预期涨幅"),
    RECOMMEND_STOCK_PAY_MUST_NOT_BELOW_MIN_PAY(20003,"荐股价格不能低于最低荐股价格"),
    RECOMMEND_STOCK_PAY_MUST_NOT_UPPER_MAX_PAY(20004,"荐股价格不能高于最高荐股价格"),

//    特殊类型的枚举
    DICTIONARY_NOT_EXIST_OR_NOT_ACTIVATED(30001,"该字典不存在或者未激活"),
    ;
    public int code;
    public String message;
    ResultEnum(int code,String message){
        this.code = code;
        this.message = message;
    }
}
