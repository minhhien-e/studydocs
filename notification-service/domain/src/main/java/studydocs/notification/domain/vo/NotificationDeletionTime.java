package studydocs.notification.domain.vo;

import java.time.LocalDateTime;

import studydocs.notification.domain.exception.notification.InvalidNotificationDeletionTimeException;

public record NotificationDeletionTime(LocalDateTime value) {
    public NotificationDeletionTime {
        if (value == null) {
            throw new InvalidNotificationDeletionTimeException();
        }
    }
}
