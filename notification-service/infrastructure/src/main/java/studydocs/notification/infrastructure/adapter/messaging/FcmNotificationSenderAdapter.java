package studydocs.notification.infrastructure.adapter.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.payload.NotificationSendPayload;
import studydocs.notification.application.port.out.messaging.NotificationSenderPort;

@Component
@RequiredArgsConstructor
public class FcmNotificationSenderAdapter implements NotificationSenderPort {

    @Override
    public void send(NotificationSendPayload payload) {

    }
}
