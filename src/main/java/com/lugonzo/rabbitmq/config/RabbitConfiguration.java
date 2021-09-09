package com.lugonzo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Autowired
    MessageQueueProperties messageQueueProperties;

    @Bean
    Queue queueA(){
        return  new Queue(messageQueueProperties.getMessagequeue(),false);
    }

    @Bean
    DirectExchange exchangeA(){
        return new DirectExchange(messageQueueProperties.getMessageexchange());
    }

    @Bean
    Binding bindingA(Queue queueA, DirectExchange exchangeA){
        return BindingBuilder.bind(queueA)
                .to(exchangeA)
                .with(messageQueueProperties.getMessagerouting());

    }

    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return  rabbitTemplate;
    }
}
