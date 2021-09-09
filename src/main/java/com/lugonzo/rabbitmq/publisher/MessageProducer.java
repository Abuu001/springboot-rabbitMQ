package com.lugonzo.rabbitmq.publisher;

import com.lugonzo.rabbitmq.config.RabbitConfiguration;
import com.lugonzo.rabbitmq.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message")
@Slf4j
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitConfiguration rabbitConfiguration;

    @PostMapping("/post")
    public String send(@RequestBody  Message message){

        log.info("::::::::::::::::::::::::::::::::::::MESSAGE PUBLISHER:::::::::::::::::::::::::::::::");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_A, "routing.A",message);

        log.info(message.toString());
        return "Message sent Succesfully";
    }

}
