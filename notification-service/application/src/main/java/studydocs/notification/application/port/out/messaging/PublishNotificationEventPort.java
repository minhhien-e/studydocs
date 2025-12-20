package studydocs.notification.application.port.out.messaging;

import studydocs.notification.application.dto.payload.NotificationReceivePayload;

public interface PublishNotificationEventPort {
    void publish(NotificationReceivePayload payload);
}
