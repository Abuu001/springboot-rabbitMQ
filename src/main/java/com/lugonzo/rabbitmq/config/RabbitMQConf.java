package com.lugonzo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {

    @Autowired
    MessageQueueProperties messageQueueProperties;

    @Bean
  public Queue queue2(){
      return new Queue(messageQueueProperties.getEmployeeequeue());
  }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(messageQueueProperties.getEmployeexchange());
   }

   @Bean
  public Binding binding2(Queue queue2,DirectExchange directExchange){
        return  BindingBuilder.bind(queue2).to(directExchange).with(messageQueueProperties.getEmployeerouting());
  }

     @Bean
     MessageConverter messageConverterEmp(){
        return new Jackson2JsonMessageConverter();
    }

  @Bean
  public AmqpTemplate rabbitTemplate2(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverterEmp());
        return rabbitTemplate;
  }
}
