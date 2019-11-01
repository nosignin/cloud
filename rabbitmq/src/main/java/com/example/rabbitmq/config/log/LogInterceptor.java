package com.example.rabbitmq.config.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final String REQUEST_ID = "requestId";

    /**
     * 每次请求时,将uuid存入,同一个请求具有相同的uuid
     * 请求结束时,再将uuid取出
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String xForwardedForHeader  = request.getHeader("X-Forwarded-For");
        String remoteAddr = request.getRemoteAddr();
        String uuid = UUID.randomUUID().toString();
        log.info(">>> request id: {} ,client ip: {},X-Forwarded-For: {}  <<<",uuid,remoteAddr,xForwardedForHeader);
        MDC.put(REQUEST_ID,uuid);
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uuid = MDC.get(REQUEST_ID);
        log.info(">>> remove request id: {} from logger <<<",uuid);
        MDC.remove(REQUEST_ID);
    }
}
