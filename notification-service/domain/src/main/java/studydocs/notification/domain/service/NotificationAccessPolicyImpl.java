package studydocs.notification.domain.service;

import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.exception.notification.NotificationAccessDeniedException;
import studydocs.notification.domain.policy.NotificationAccessPolicy;

import java.util.UUID;

public class NotificationAccessPolicyImpl implements NotificationAccessPolicy {
    @Override
    public void checkCanAccess(NotificationRecipient notification, UUID recipientId) {
        if (!notification.getRecipientId().equals(recipientId)) {
            throw new NotificationAccessDeniedException();
        }
    }
}
