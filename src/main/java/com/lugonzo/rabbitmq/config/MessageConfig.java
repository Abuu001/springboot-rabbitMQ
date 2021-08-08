package com.lugonzo.rabbitmq.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    public static final String QUEUE ="java_techy_queue";
    public static final String EXCHANGE="java_techy_exchange";
    public static final String ROUTING_KEY="java_techy_routingKey";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean    // to convert to objects
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

   @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return  rabbitTemplate;
   }
}
