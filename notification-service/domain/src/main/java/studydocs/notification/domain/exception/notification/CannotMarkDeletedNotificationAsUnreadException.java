package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class CannotMarkDeletedNotificationAsUnreadException extends DomainException {
    public CannotMarkDeletedNotificationAsUnreadException() {
        super("Cannot mark deleted notification as unread", DomainErrorCode.NOTIFICATION_RECIPIENT_DELETED);
    }
}
