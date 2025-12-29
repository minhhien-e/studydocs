package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationCreationTimeException extends DomainException {
    public InvalidNotificationCreationTimeException() {
        super("Notification creation time cannot be null", DomainErrorCode.INVALID_NOTIFICATION_CREATION_TIME);
    }
}
