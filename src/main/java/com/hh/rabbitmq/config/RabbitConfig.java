package com.hh.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final String TOPIC_QUEUE = "topic";
    private static final String MESSAGE_QUEUE = "message";
    private static final String EXCHANGE_QUEUE = "exchange";

    @Bean
    public Queue topicQueue(){
        return new Queue(TOPIC_QUEUE,true);
    }

    @Bean
    public Queue messageQueue(){
        return new Queue(MESSAGE_QUEUE,true);
    }

    @Bean
    public Queue exchangeQueue(){
        return new Queue(EXCHANGE_QUEUE,true);
    }

}
