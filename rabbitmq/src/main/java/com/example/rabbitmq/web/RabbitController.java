package com.example.rabbitmq.web;

import com.example.rabbitmq.entity.RabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rabbit")
@RestController
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public Object send(@RequestBody RabbitMessage rabbitMessage){
        String myDirectExchange = "myDirectExchange";
        String routingKey = "mine.direct";
        String message = "this is a message";
        rabbitTemplate.convertAndSend(myDirectExchange, routingKey, message);
        return "消息发送成功";
    }
}
