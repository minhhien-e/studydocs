package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import studydocs.notification.application.port.out.messaging.NotificationMessagePort;
import studydocs.notification.domain.event.NotificationReceivedEvent;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPublisher implements NotificationMessagePort {

//    private final AmqpTemplate rabbitTemplate;

    @Override
    public void publish(NotificationReceivedEvent event) {
//        rabbitTemplate.convertAndSend(
//                RabbitMQConfig.NOTIFICATION_EXCHANGE,
//                RabbitMQConfig.NOTIFICATION_RECEIVED_ROUTING_KEY,
//                event
//        );
    }
}
