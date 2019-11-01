package com.example.rabbitmq.entity;

import com.example.rabbitmq.enums.BusinessErrorEnum;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ErrorResult implements Serializable {
    private Integer errCode;
    private String errMsg;
    public ErrorResult(BusinessErrorEnum businessErrorEnum){
        this.errCode = businessErrorEnum.errCode;
        this.errMsg = businessErrorEnum.errMsg;
    }
}
