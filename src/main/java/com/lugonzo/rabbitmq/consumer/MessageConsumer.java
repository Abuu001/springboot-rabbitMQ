package com.lugonzo.rabbitmq.consumer;

import com.lugonzo.rabbitmq.config.RabbitConfiguration;
import com.lugonzo.rabbitmq.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @RabbitListener(queues = RabbitConfiguration.QUEUE_A)
    private void receiveMessage(Message message){
        log.info(":::::::::::::::::::::::::MESSAGE CONSUMER::::::::::::::::::::::::::::;;;;");
        log.info("Message received Successfully {consumer}" +message);
    }
}
