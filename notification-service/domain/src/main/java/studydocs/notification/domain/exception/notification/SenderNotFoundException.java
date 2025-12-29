package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class SenderNotFoundException extends DomainException {
    public SenderNotFoundException(UUID senderId) {
        super("Sender not found: " + senderId, DomainErrorCode.SENDER_NOT_FOUND);
    }
}
