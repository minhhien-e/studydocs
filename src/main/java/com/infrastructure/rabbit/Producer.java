//package com.infrastructure.rabbit;
//
//import com.config.RabbitConfig;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Producer {
//    private final RabbitTemplate rabbitTemplate;
//
//    public Producer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void sendMessage(String message) {
//        rabbitTemplate.convertAndSend(
//                RabbitConfig.EXCHANGE_NAME,
//                RabbitConfig.ROUTING_KEY,
//                message
//        );
//        System.out.println("Sent: " + message);
//    }
//}
