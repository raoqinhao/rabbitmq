package com.hh.rabbitmq.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Product {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String send(String message) {
        try {
            amqpTemplate.convertAndSend("topic",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
