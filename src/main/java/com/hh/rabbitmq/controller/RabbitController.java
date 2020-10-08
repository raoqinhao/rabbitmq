package com.hh.rabbitmq.controller;

import com.hh.rabbitmq.rabbit.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RabbitController {

    @Autowired
    private Product product;

    @RequestMapping(value = "/sendTopic",method = RequestMethod.POST)
    @ResponseBody
    private String sendTopic(@RequestBody String json) {
        String send = product.sendTopic(json);
        return send;
    }

    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    private String sendMessage(@RequestBody String json) {
        String send = product.sendMessage(json);
        return send;
    }

}
