package com.hh.rabbitmq.rabbit;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues="topic")
    @RabbitHandler
    public void topic(String msg) {
        System.out.println(msg);
    }

    @RabbitListener(queues="topic")
    @RabbitHandler
    public void topic1(String msg) {
        System.out.println(msg + " 1");
    }


    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "exchange", durable = "true"),
            exchange=@Exchange(
            value = "spring.topic.exchange",
            ignoreDeclarationExceptions = "true",
            type = ExchangeTypes.TOPIC),
            key = {"person.*"}))
    @RabbitHandler
    public void topicMessage(String msg) {
        System.out.println(msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("spring.work.queue"))
    @RabbitHandler
    public void workPatternMessage(String msg) {
        System.out.println(msg);
    }

}
