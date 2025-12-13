package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.notification.InvalidNotificationSnapshotSubjectException;

public record NotificationSnapshotSubject(String value) {
    public NotificationSnapshotSubject {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationSnapshotSubjectException();
        }
        if (value.length() > 500) {
            throw new InvalidNotificationSnapshotSubjectException("Subject cannot exceed 500 characters");
        }
    }
}
