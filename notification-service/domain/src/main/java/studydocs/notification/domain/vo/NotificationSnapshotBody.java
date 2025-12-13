package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.notification.InvalidNotificationSnapshotBodyException;

public record NotificationSnapshotBody(String value) {
    public NotificationSnapshotBody {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationSnapshotBodyException();
        }
        if (value.length() > 10000) {
            throw new InvalidNotificationSnapshotBodyException("Body cannot exceed 10000 characters");
        }
    }
}
