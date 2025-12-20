package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.NotificationReceivePayload;
import studydocs.notification.application.port.out.messaging.PublishNotificationEventPort;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitNotificationEventEventPublisher implements PublishNotificationEventPort {

    private final AmqpTemplate rabbitTemplate;

    @Override
    public void publish(NotificationReceivePayload payload) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTIFICATION_EXCHANGE,
                RabbitMQConfig.NOTIFICATION_RECEIVED_ROUTING_KEY,
                payload
        );
    }
}
