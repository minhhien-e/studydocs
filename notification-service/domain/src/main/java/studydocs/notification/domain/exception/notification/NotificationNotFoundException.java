package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationNotFoundException extends DomainException {
    public NotificationNotFoundException(UUID notificationId) {
        super("Notification not found with id: " + notificationId, DomainErrorCode.NOTIFICATION_NOT_FOUND);
    }
}