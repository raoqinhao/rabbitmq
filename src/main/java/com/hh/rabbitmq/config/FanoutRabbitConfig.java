package com.hh.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("spring.fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("spring.fanout.queue2");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("spring.fanout.exchange");
    }

    @Bean
    public Binding bing1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bing2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

}
