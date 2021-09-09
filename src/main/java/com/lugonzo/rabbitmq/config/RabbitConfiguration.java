package com.lugonzo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String QUEUE_A="queue.A";
    public static final String ROUTING_A="routing.A";
    public static final String EXCHANGE_A="exchange.direct";

    @Bean
    Queue queueA(){
        return  new Queue(QUEUE_A,false);
    }

    @Bean
    DirectExchange exchangeA(){
        return new DirectExchange("exchange.direct");
    }

    @Bean
    Binding bindingA(Queue queueA, DirectExchange exchangeA){
        return BindingBuilder.bind(queueA)
                .to(exchangeA)
                .with(ROUTING_A);

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
