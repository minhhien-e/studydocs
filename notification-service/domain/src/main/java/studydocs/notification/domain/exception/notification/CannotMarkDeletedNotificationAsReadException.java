package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class CannotMarkDeletedNotificationAsReadException extends DomainException {
    public CannotMarkDeletedNotificationAsReadException() {
        super("Cannot mark deleted notification as read", DomainErrorCode.NOTIFICATION_RECIPIENT_DELETED);
    }
}
