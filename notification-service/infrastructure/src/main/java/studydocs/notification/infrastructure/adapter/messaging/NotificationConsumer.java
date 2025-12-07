package studydocs.notification.infrastructure.adapter.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notification.domain.event.NotificationReceivedEvent;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@Slf4j
public class NotificationConsumer {

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_RECEIVED_QUEUE)
    public void handleNotificationReceived(NotificationReceivedEvent event) {
        log.info("Received NotificationReceivedEvent from Queue: {}", event);
        // Business Logic here (e.g., Send Email, Push Notification, etc.)
        // For now, we just log it.
        log.info("Processed Notification for Recipient ID: {}", event.recipientId());
    }
}
