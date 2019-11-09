package com.example.redis.aop;

import com.alibaba.fastjson.JSON;
import com.example.redis.entity.CommHttpResult;
import com.example.redis.enums.ResultEnum;
import com.example.redis.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
// 必须加上order,否则不生效,全局日志aop的顺序应该在其他aop之前,数字越小越先加载
@Order(1)
public class WebLogAspect {
    /*
    整个表达式可以分为五个部分：
1、execution(): 表达式主体。
2、第一个*号：表示返回类型， *号表示所有的类型。
3、包名：表示需要拦截的包名.
4、第二个*号：表示类名，*号表示所有的类。
5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
     */
    public static final String POINTCUT="execution(* com.example.redis.web.*.*(..))";
    @Pointcut(POINTCUT)
    public void pointCut(){}

    /*
    前置通知：在某连接点之前执行的通知，但这个通知不能阻止连接点前的执行
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
//        接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        if("application/json".equals(request.getHeader("content-Type"))){
            Object[] args = joinPoint.getArgs();
            log.info(">>> URL: {} ,类: {},方法: {},请求参数: {}  <<<",request.getRequestURL().toString(),
                    joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),JSON.toJSONString(args[0]));
        }else {
            Enumeration<String> enu = request.getParameterNames();
            Map<String, String> map = new HashMap<>();
            while(enu.hasMoreElements()){
                String name = enu.nextElement();
                map.put(name,request.getParameter(name));
            }
            log.info(">>> URL: {} ,类: {},方法: {},请求参数: {} <<<",request.getRequestURL().toString(),
                    joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),JSON.toJSONString(map));
        }
    }

    /*
    后置通知
    AfterReturning配置必须有argNames参数，且参数值和returning值一样，这样在织入代码里面便可通过returning的值获取被织入函数的返回值。
     */
    @AfterReturning(returning = "result",pointcut ="pointCut()" )
    public void after(JoinPoint joinPoint,Object result){
//        处理完请求，返回内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info(">>> URL: {} ,类: {},方法: {},响应报文: {} <<<",request.getRequestURL().toString(),
                joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),JSON.toJSONString(result));
    }

    /**
     * 环绕通知
     * 也可以写成 @Around(POINTCUT)
     * @return
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint){
        long startTime = System.currentTimeMillis();
        Object resultData = null;
        Object[] args = joinPoint.getArgs();
        try {
            resultData = joinPoint.proceed(args);
            long endTime = System.currentTimeMillis();
            log.info(">>> 请求接口耗时: {} ms <<<",(endTime-startTime));
            return new CommHttpResult("success",resultData);
        } catch (Throwable throwable) {
            long endTime = System.currentTimeMillis();
            log.error(">>> 请求接口出现异常! 耗时: {} ms <<<",(endTime-startTime));
            log.error(">>> {} <<<",throwable.getMessage());
            if(throwable instanceof BaseException){
                return new CommHttpResult("fail",throwable.getMessage());
            }else {
                return new CommHttpResult("fail", ResultEnum.SYSTEM_ERROR.message);
            }
        }
    }
}
