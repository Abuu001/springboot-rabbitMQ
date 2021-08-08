package com.lugonzo.rabbitmq.consumer;

import com.lugonzo.rabbitmq.config.MessageConfig;
import com.lugonzo.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void consumerMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from queue  : "+orderStatus);
    }
}
