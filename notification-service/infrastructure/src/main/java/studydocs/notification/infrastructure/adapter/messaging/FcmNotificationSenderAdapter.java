package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.NotificationSendPayload;
import studydocs.notification.application.port.out.messaging.NotificationSenderPort;
import studydocs.notification.infrastructure.messaging.FirebaseMessagingService;

@Component
@RequiredArgsConstructor
public class FcmNotificationSenderAdapter implements NotificationSenderPort {
    private final FirebaseMessagingService firebaseMessagingService;

    @Override
    public void send(NotificationSendPayload payload) {
        firebaseMessagingService.sendMultiNotification(payload.subject(), payload.body(), payload.destinations());
    }

}
