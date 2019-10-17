package com.example.quickdevelop.component.constant;

import com.example.quickdevelop.component.common.Result;

public interface ResultConstant {
    interface SYSTEM{
        Result HYSTRIX_RESULT = new Result("熔断处理");
        Result SERVICE_DEGRADATION_RESULT = new Result("服务降级");//feign服务降级，错误码为0
        Result SYSTEM_EXCEPTION_RESULT = new Result(500, "系统错误");
        Result REQUEST_METHOD_EXCEPTION_RESULT = new Result(405, "请求方式错误");
        Result ACCESS_DENIED_EXCEPTION_RESULT = new Result(401, "无权访问");
        Result HTTP_MESSAGE_NOT_READABLE_EXCEPTION_RESULT = new Result(405, "请用JSON方式传参");

    }


}
