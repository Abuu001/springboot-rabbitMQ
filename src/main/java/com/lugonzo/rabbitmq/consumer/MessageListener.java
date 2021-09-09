package com.lugonzo.rabbitmq.consumer;

import com.lugonzo.rabbitmq.config.MQConfig;
import com.lugonzo.rabbitmq.dto.CustomMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @RabbitListener(queues = MQConfig._QUEUE    )
    public void listener(CustomMessage message){
        System.out.println(message);
        log.info("::::::::::::::::::::CUSTOM MESSAGE LISTENER::::::::::::::::::::::::");
        log.info(String.valueOf(message));
    }
}
