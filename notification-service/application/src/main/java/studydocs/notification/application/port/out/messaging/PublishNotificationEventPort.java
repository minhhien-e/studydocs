package studydocs.notification.application.port.out.messaging;

import studydocs.notification.application.dto.payload.NotificationRecipientReadyPayload;

public interface PublishNotificationEventPort {
    void publish(NotificationRecipientReadyPayload payload);
}
