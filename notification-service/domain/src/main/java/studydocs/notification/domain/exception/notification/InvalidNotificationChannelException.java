package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationChannelException extends DomainException {
    public InvalidNotificationChannelException() {
        super("Notification channel cannot be null or empty", DomainErrorCode.INVALID_NOTIFICATION_CHANNEL);
    }
}
