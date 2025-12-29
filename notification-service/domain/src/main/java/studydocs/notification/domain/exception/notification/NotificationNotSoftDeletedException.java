package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationNotSoftDeletedException extends DomainException {
    public NotificationNotSoftDeletedException(UUID recipientId) {
        super("Notification" + recipientId + " is not soft deleted, so it cannot be restored.", DomainErrorCode.NOTIFICATION_NOT_SOFT_DELETED);
    }
}
