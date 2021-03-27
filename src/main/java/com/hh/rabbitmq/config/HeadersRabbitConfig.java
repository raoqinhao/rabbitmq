package com.hh.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class HeadersRabbitConfig {

    @Bean
    public Queue headersQueue1() {
        return new Queue("spring.headers.queue1");
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue("spring.headers.queue2");
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("spring.headers.exchange");
    }

    @Bean
    public Binding headersBinding1() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue1");
        header.put("bindType", "whereAll");
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAll(header).match();
    }

    @Bean
    public Binding headersBinding2() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue2");
        header.put("bindType", "whereAny");
        return BindingBuilder.bind(headersQueue2()).to(headersExchange()).whereAny(header).match();
    }
}
