package com.hh.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue topicQueue1() {
        return new Queue("spring.topic.queue1");
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue("spring.topic.queue2");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("spring.topic.exchange");
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("com.hh.#");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("#.organization");
    }
}
