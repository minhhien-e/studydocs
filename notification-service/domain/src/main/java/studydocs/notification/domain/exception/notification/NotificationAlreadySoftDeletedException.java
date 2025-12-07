package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationAlreadySoftDeletedException extends DomainException {
    public NotificationAlreadySoftDeletedException(UUID notificationId) {
        super("Notification" + notificationId + " has already been soft deleted.", DomainErrorCode.NOTIFICATION_ALREADY_SOFT_DELETED);
    }
}
