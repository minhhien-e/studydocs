package studydocs.notification.application.port.out.messaging;

import studydocs.notification.application.dto.payload.NotificationSendPayload;

public interface NotificationSenderPort {
    void send(NotificationSendPayload payload);
}
