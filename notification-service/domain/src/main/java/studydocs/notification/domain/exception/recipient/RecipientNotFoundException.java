package studydocs.notification.domain.exception.recipient;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class RecipientNotFoundException extends DomainException {
    public RecipientNotFoundException(UUID recipientId) {
        super("Notification recipient not found with id: " + recipientId, DomainErrorCode.RECIPIENT_NOT_FOUND);
    }
}
