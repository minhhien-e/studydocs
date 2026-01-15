package studydocs.notification.domain.exception.notification;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidNotificationSnapshotSubjectException extends DomainException {
    public InvalidNotificationSnapshotSubjectException() {
        super("Notification snapshot subject cannot be null or blank", DomainErrorCode.INVALID_NOTIFICATION_SNAPSHOT_SUBJECT);
    }

    public InvalidNotificationSnapshotSubjectException(String message) {
        super(message, DomainErrorCode.INVALID_NOTIFICATION_SNAPSHOT_SUBJECT);
    }
}
