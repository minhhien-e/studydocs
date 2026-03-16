package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class NotificationAccessDeniedException extends DomainException {
    public NotificationAccessDeniedException() {
        super("Access to notification is denied", DomainErrorCode.ACCESS_DENIED);
    }
}
