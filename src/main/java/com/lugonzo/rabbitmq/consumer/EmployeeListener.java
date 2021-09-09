package com.lugonzo.rabbitmq.consumer;

import com.lugonzo.rabbitmq.config.MQConfig;
import com.lugonzo.rabbitmq.config.RabbitMQConf;
import com.lugonzo.rabbitmq.dto.CustomMessage;
import com.lugonzo.rabbitmq.dto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeListener {

    @RabbitListener(queues = "employee.queuename")
    public void EmployeeListener(Employee employee){
        log.info("::::::::::::::::::::CUSTOM MESSAGE LISTENER::::::::::::::::::::::::");
        log.info(String.valueOf(employee));
    }
}
