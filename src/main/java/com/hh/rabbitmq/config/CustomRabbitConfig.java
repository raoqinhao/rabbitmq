package com.hh.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomRabbitConfig {

    @Bean
    public Queue customQueue() {
        return new Queue("spring.custom.ackQueue");
    }

    @Bean
    public FanoutExchange customFanoutExchange() {
        return new FanoutExchange("spring.custom.ackExchange",true,false);
    }

    @Bean
    public Binding customBinding() {
        return BindingBuilder.bind(customQueue()).to(customFanoutExchange());
    }
}
