package studydocs.notification.infrastructure.exception;

import studydocs.notification.infrastructure.enums.InfrastructureErrorCode;
import studydocs.notification.infrastructure.exception.base.InfrastructureException;

import java.util.UUID;

public class NotificationRecipientNotFoundException extends InfrastructureException {

    public NotificationRecipientNotFoundException(UUID id) {
        super("Notification recipient not found with id: " + id, InfrastructureErrorCode.NOTIFICATION_RECIPIENT_NOT_FOUND);
    }
}
