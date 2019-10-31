package com.example.rabbitmq.entity;

import lombok.Data;

@Data
public class RabbitMessage {
    String myDirectExchange;
    String routingKey;
    String message;
}
