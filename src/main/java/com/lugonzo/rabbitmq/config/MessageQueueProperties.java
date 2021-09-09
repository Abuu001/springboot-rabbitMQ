package com.lugonzo.rabbitmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("message")
public class MessageQueueProperties {

    public   String messagequeue;
    public   String messagerouting;
    public   String messageexchange;
    public   String employeeequeue;
    public   String employeerouting;
    public   String employeexchange;

}
