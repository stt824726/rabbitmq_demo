package com.stt.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component

public class TopicReceiver {

    @RabbitHandler
    @RabbitListener(queues = "topic.messages")
    public void process(String message) {
        System.out.println("topic.messages Receiver1  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "topic.message")
    public void process2(String message) {
        System.out.println("topic.message Receiver1  : " + message);
    }

}
