package com.hh.rabbitmq.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Product {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String sendTopic(String message) {
        try {
            amqpTemplate.convertAndSend("topic",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String sendMessage(String message) {
        try {
            amqpTemplate.convertAndSend("message",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String sendTopicMessage(String message) {
        try {
            amqpTemplate.convertAndSend("spring.topic.exchange","person.insert",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

}
