package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SendNotificationCommand;
import studydocs.notification.application.dto.payload.NotificationReceivePayload;
import studydocs.notification.application.port.in.usecase.notification.SendNotificationUseCasePort;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final SendNotificationUseCasePort useCase;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_RECEIVED_QUEUE)
    public void handleNotificationReceived(NotificationReceivePayload payload) {
        useCase.execute(new SendNotificationCommand(payload.notificationId(), payload.notificationRecipientId()));
    }
}
