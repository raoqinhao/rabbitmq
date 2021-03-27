package com.hh.rabbitmq.controller;


import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class RabbitController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "sendMessage",method = RequestMethod.POST)
    public String sendMessage(@RequestBody String message) {
        try {
            amqpTemplate.convertAndSend("spring.direct.exchange", "spring.routing", message, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration("10000");  // 设置消息过期时间，过期后，由于配置config配置了当前队列会将过期的消息转发到死性队列中。
                    return message;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "sendFanoutMessage",method = RequestMethod.POST)
    public String sendFanoutMessage(@RequestBody String message) {
        try {
            amqpTemplate.convertAndSend("spring.fanout.exchange", "", message, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
//                    message.getMessageProperties().setExpiration("10000");  // 设置消息过期时间，过期后，由于配置config配置了当前队列会将过期的消息转发到死性队列中。
                    return message;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "sendTopicMessage",method = RequestMethod.POST)
    public String sendTopicMessage(@RequestBody String message) {
        try {
            amqpTemplate.convertAndSend("spring.topic.exchange","com.hh.organization",message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "sendHeadersMessage",method = RequestMethod.POST)
    public String sendHeadersMessage(@RequestBody String message) {
        try {
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setHeader("queue","queue1");
            int i = (int) (new Random().nextDouble() * 10);
            if (i % 10 == 0) {
                messageProperties.setHeader("bindType","whereAll");
            } else {
                messageProperties.setHeader("bindType","whereAny");
            }
            Message mesProperties = new Message(message.getBytes(), messageProperties);
            amqpTemplate.convertAndSend("spring.headers.exchange","",mesProperties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
