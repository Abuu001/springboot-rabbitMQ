package com.lugonzo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MQConfig {

    public static final String _QUEUE ="message_queue";
    public static final String EXCHANGE ="message_exchange";
    public static final String ROUTING_KEY ="message_routingKey";

    @Bean
    public Queue queue3(){
        return new Queue(_QUEUE);
    }

    @Bean
    public TopicExchange exchange3(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding3(){
        return BindingBuilder
                .bind(queue3())
                .to(exchange3())
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter3(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template3(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter3());
        return template;
    }

}
