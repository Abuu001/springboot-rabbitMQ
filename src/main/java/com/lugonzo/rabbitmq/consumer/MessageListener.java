package com.lugonzo.rabbitmq.consumer;

import com.lugonzo.rabbitmq.config.MQConfig;
import com.lugonzo.rabbitmq.dto.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig._QUEUE    )
    public void listener(CustomMessage message){
        System.out.println(message);
    }
}
