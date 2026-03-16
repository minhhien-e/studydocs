package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationTypeException extends DomainException {
    public InvalidNotificationTypeException() {
        super("Notification type cannot be null or empty", DomainErrorCode.INVALID_NOTIFICATION_TYPE);
    }
}
