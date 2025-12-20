package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notification.domain.event.NotificationReceivedEvent;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final studydocs.notification.application.service.usecase.notification.SendNotificationUseCase sendNotificationUseCase;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_RECEIVED_QUEUE)
    public void handleNotificationReceived(NotificationReceivedEvent event) {
        log.info("Received notification: {}", event);
        try {
            sendNotificationUseCase.execute(event.notificationId(), event.notificationRecipientId());
        } catch (Exception e) {
            log.error("Error processing notification event: {}", event, e);
            // Throwing exception to trigger RabbitMQ retry/DLQ if needed
            throw e; 
        }
    }
}
