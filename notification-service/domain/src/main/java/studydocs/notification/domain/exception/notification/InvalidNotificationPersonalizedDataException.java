package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationPersonalizedDataException extends DomainException {
    public InvalidNotificationPersonalizedDataException() {
        super("Personalized data cannot be null", DomainErrorCode.INVALID_PERSONALIZED_DATA);
    }
}
