//package com.infrastructure.rabbit;
//
//import com.config.RabbitConfig;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Consumer {
//
//    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
//    public void receiveMessage(String message) {
//        System.out.println("Received: " + message);
//    }
//}
//
