package com.hh.rabbitmq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceiverMessage {

    @RabbitListener(queues = "spring.direct.ttl.queue")
    @RabbitHandler
    public void getTtlMessage(String message) {
        System.out.println(message);
    }

//    @RabbitListener(queues = "spring.direct.queue")
    @RabbitHandler
    public void getMessage(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = "spring.fanout.queue1")
    @RabbitHandler
    public void getFanoutQueue1(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = "spring.topic.queue1")
    @RabbitHandler
    public void getTopicQueue1(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = "spring.headers.queue1")
    @RabbitHandler
    public void getHeadersQueue1(String message) {
        String[] split = message.split(",");
        byte[] aByte = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            Byte aByte1 = Byte.valueOf(split[i]);
            aByte[i] = aByte1;
        }
        System.out.println(new String(aByte));
    }

    @RabbitListener(queues = "spring.custom.ackQueue")
    @RabbitHandler
    public void getCustomMessage(Message message, Channel channel) throws IOException {
        try {
            System.out.println(new String(message.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
