package com.stt.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    @RabbitHandler
    @RabbitListener(queues = "direct.A")
    public void processA(String message) {
        System.out.println("direct.A Receiver1  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "direct.B")
    public void processB(String message) {
        System.out.println("direct.B Receiver1  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "direct.C")
    public void processC(String message) {
        System.out.println("direct.C Receiver1  : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "direct.D")
    public void processD(String message) {
        System.out.println("direct.D Receiver1  : " + message);
    }
}
