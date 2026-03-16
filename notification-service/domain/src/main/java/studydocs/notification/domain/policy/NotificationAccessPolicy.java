package studydocs.notification.domain.policy;

import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.aggregate.NotificationRecipient;

import java.util.UUID;

public interface NotificationAccessPolicy {
    void checkCanAccess(NotificationRecipient notification, UUID recipientId);
}
