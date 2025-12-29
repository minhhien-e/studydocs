package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationDeletionTimeException extends DomainException {
    public InvalidNotificationDeletionTimeException() {
        super("Notification deletion time cannot be null", DomainErrorCode.INVALID_NOTIFICATION_DELETION_TIME);
    }
}
