package com.lugonzo.rabbitmq.publisher;

import com.lugonzo.rabbitmq.config.RabbitMQConf;
import com.lugonzo.rabbitmq.dto.Employee;
import com.lugonzo.rabbitmq.dto.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeePublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/producer")
    public String getEmployee(@RequestParam("empName") String empName){

        var employee1 = Employee.builder()
                .empId(UUID.randomUUID().toString())
                .empName("Martha")
                .gender(Gender.FEMALE)
                .age(21)
                .build();

        log.info(employee1.toString());
        rabbitTemplate.convertAndSend(RabbitMQConf.EXCHANGENAME, RabbitMQConf.ROUTINGKEY,employee1.toString());

        return "Message sent to the RabbitMQ JavaInUse Successfully 🤡🤡🤡";
    }
}
