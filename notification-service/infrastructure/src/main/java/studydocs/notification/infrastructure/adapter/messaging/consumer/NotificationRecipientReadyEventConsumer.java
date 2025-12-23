package studydocs.notification.infrastructure.adapter.messaging.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SendNotificationCommand;
import studydocs.notification.application.dto.payload.NotificationRecipientReadyPayload;
import studydocs.notification.application.port.in.usecase.notification.SendNotificationUseCasePort;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
public class NotificationRecipientReadyEventConsumer {
    private final SendNotificationUseCasePort useCase;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_RECIPIENT_READY_QUEUE)
    public void handleNotificationReceived(NotificationRecipientReadyPayload payload) {
        useCase.execute(new SendNotificationCommand(payload.notificationId(), payload.notificationRecipientId()));
    }
}
