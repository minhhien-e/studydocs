package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.SendNotificationCommand;
import studydocs.notification.application.dto.payload.NotificationReceivePayload;
import studydocs.notification.application.port.in.bus.MediatorBusPort;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {
    private final MediatorBusPort bus;

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_RECEIVED_QUEUE)
    public void handleNotificationReceived(NotificationReceivePayload payload) {
        bus.send(new SendNotificationCommand(payload.notificationId(), payload.notificationRecipientId()));
    }
}
