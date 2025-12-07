package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationCategoryException extends DomainException {
    public InvalidNotificationCategoryException() {
        super("Notification category cannot be null or empty", DomainErrorCode.INVALID_NOTIFICATION_CATEGORY);
    }
}
