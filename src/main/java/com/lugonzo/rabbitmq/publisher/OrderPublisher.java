package com.lugonzo.rabbitmq.publisher;

import com.lugonzo.rabbitmq.config.MessageConfig;
import com.lugonzo.rabbitmq.dto.Order;
import com.lugonzo.rabbitmq.dto.OrderStatus;
import com.lugonzo.rabbitmq.dto.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequestMapping("/api/v1/order")
@RestController
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @GetMapping
    public String bb(){
        return "Abuu";
    }

    @PostMapping
    public String bookOrder(@RequestBody Order newOrder){
        newOrder.setOrderId(UUID.randomUUID().toString());
        var orderStatus =  OrderStatus.builder()
                .order(newOrder)
                .status(Status.PROCESS)
                .message("order placed successfully  !!! 👍👍👍")
                .build();

        log.info(orderStatus.toString());

        template.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,orderStatus);
        return "Success !!! 🥳🥳🥳";
    }

}
