package com.example.rabbitmq.exception;


import com.example.rabbitmq.enums.BusinessErrorEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private BusinessErrorEnum businessErrorEnum;

    public BusinessException(BusinessErrorEnum businessErrorEnum){
        this.businessErrorEnum = businessErrorEnum;
    }

}
