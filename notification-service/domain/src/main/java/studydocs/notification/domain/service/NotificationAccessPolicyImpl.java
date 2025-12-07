package studydocs.notification.domain.service;

import studydocs.notification.domain.aggregate.Notification;
import studydocs.notification.domain.exception.notification.NotificationAccessDeniedException;
import studydocs.notification.domain.policy.NotificationAccessPolicy;

import java.util.UUID;

public class NotificationAccessPolicyImpl implements NotificationAccessPolicy {
    @Override
    public void checkCanAccess(Notification notification, UUID recipientId) {
        var canAccess = notification.getNotificationRecipients().stream().anyMatch(recipient->recipient.getRecipientId().equals(recipientId));
        if (!canAccess) {
            throw new NotificationAccessDeniedException();
        }
    }
}
