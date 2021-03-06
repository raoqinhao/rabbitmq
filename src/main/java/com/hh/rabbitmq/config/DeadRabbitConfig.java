package com.hh.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadRabbitConfig {

    @Bean
    public Queue ttlQueue() {
        return new Queue("spring.direct.ttl.queue");
    }

    @Bean
    public Queue deadQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange","spring.direct.ttl.exchange");
        args.put("x-dead-letter-routing-key","spring.routing");
//        args.put("x-message-ttl",10000);  // 设置消息过期时间无效果
//        args.put("x-expires",10000);  // 设置队列过期时间无效果
        return new Queue("spring.direct.deadQueue",true,false,false,args);
    }

    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("spring.direct.ttl.exchange");
    }

    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange("spring.direct.deadExchange");
    }

    @Bean
    public Binding queueBinding1() {
        return BindingBuilder.bind(ttlQueue()).to(ttlDirectExchange()).with("spring.routing");
    }

    @Bean
    public Binding queueBinding2() {
        return BindingBuilder.bind(deadQueue()).to(deadDirectExchange()).with("spring.deadRouting");
    }

}
