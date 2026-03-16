package studydocs.notification.domain.exception.recipient;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationRecipientNotFoundException extends DomainException {

    public NotificationRecipientNotFoundException(UUID id) {
        super("Notification recipient not found with id: " + id, DomainErrorCode.NOTIFICATION_RECIPIENT_NOT_FOUND);
    }
}
