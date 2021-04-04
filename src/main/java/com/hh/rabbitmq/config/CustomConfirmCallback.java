package com.hh.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        // 确认机制，ack 返回true表示数据成功发送到rabbitmq服务器中。

        System.out.println(correlationData + " " + ack + " " + s);
    }
}
