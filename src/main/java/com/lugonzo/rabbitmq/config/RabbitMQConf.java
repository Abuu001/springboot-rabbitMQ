package com.lugonzo.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConf {

    public static final String QUEUENAME="employee_queuename";
    public static final String EXCHANGENAME="employee_exchangename";
    public static final String ROUTINGKEY="employee_routingkey";

    @Bean
  public Queue queue2(){
      return new Queue(QUEUENAME);
  }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGENAME);
   }

   @Bean
  public Binding binding2(Queue queue2,DirectExchange directExchange){
        return  BindingBuilder.bind(queue2()).to(directExchange).with(ROUTINGKEY);
  }

     @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

  @Bean
  public AmqpTemplate rabbitTemplate2(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
  }
}
