package com.example.rabbitmq.web;

import com.example.rabbitmq.enums.BusinessErrorEnum;
import com.example.rabbitmq.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/exception")
@RestController
@Slf4j
public class ExceptionController {
    @PostMapping("/exception")
    public void exception(){
        log.info(">>> 抛出系统异常 <<<");
        throw new RuntimeException("未知的异常信息");
    }

    @PostMapping("/business/exception")
    public void businessException() {
        log.info(">>> 抛出业务异常 <<<");
        throw new BusinessException(BusinessErrorEnum.USER_NOT_LOGIN);
    }
}
