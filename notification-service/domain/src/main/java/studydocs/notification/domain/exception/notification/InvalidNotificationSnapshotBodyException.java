package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationSnapshotBodyException extends DomainException {
    public InvalidNotificationSnapshotBodyException() {
        super("Notification snapshot body cannot be null or blank", DomainErrorCode.INVALID_NOTIFICATION_SNAPSHOT_BODY);
    }

    public InvalidNotificationSnapshotBodyException(String message) {
        super(message, DomainErrorCode.INVALID_NOTIFICATION_SNAPSHOT_BODY);
    }
}
