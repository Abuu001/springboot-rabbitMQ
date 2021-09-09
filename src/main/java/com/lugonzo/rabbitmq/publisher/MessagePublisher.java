package com.lugonzo.rabbitmq.publisher;

import com.lugonzo.rabbitmq.config.MQConfig;
import com.lugonzo.rabbitmq.dto.CustomMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/message")
public class MessagePublisher {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage newMessage){
        newMessage.setMessageId(UUID.randomUUID().toString());
        newMessage.setMessageDate(new Date());

        System.out.println("::::::::::::::::::::Mess PUBLISHER:::::::::::::::::::::::::");
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,newMessage);

        return "Message Published 🚀🚀🚀";
    }
}
