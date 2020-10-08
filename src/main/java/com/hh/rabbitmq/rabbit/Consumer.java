package com.hh.rabbitmq.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="topic")
public class Consumer {

    @RabbitHandler
    public void accept(String msg) {
        System.out.println(msg);
    }

}
