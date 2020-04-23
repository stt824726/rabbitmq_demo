package com.stt.topic;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicReceiver {

    @RabbitHandler
    @RabbitListener(queues = "topic.messages")
    public void process(String message) {
        System.out.println("topic.messages1 Receiver1  : " + message);
    }



    @RabbitHandler
    @RabbitListener(queues = "topic.message")
    public void process2(String message, Channel channel) throws Exception{
        System.out.println("topic.message Receiver1  : " + message);
        channel.basicReject(1L, true);
    }

}
