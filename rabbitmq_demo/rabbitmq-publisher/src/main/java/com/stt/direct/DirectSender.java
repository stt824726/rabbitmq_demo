package com.stt.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hi, direct msg ";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("directExchange","direct", context);
    }

}
