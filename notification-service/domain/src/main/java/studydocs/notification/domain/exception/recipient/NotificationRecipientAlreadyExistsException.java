package studydocs.notification.domain.exception.recipient;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationRecipientAlreadyExistsException extends DomainException {
    public NotificationRecipientAlreadyExistsException(UUID notificationId, UUID recipientId) {
        super(String.format(
                "NotificationRecipient already exists for notificationId=%s and recipientId=%s",
                notificationId,
                recipientId
        ), DomainErrorCode.NOTIFICATION_RECIPIENT_ALREADY_EXISTS);
    }
}
