package studydocs.notification.domain.policy;

import java.util.UUID;

public interface NotificationSendPolicy {
    void ensureCanCreate(
            UUID senderId,
            UUID templateId
    );

    void ensureCanReceive(UUID notificationId, UUID recipientId);

    void ensureCanSend(UUID recipientId);

}
