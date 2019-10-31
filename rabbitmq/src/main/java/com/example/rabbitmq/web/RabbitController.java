package com.example.rabbitmq.web;

import com.example.rabbitmq.entity.RabbitMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/rabbit")
@RestController
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 访问localhost:8080/rabbit/send得到访问结果
     * @param rabbitMessage
     * @return
     */
    @PostMapping("/send")
    public Object send(@RequestBody RabbitMessage rabbitMessage){
        String myDirectExchange = rabbitMessage.getMyDirectExchange();
        String routingKey = rabbitMessage.getRoutingKey();
        String message = rabbitMessage.getMessage();
        rabbitTemplate.convertAndSend(myDirectExchange, routingKey, message);
        return "消息发送成功";
    }
}
