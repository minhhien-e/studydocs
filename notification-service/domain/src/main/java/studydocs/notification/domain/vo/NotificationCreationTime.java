package studydocs.notification.domain.vo;

import java.time.LocalDateTime;

import studydocs.notification.domain.exception.notification.InvalidNotificationCreationTimeException;

public record NotificationCreationTime(LocalDateTime value) {
    public NotificationCreationTime {
        if (value == null) {
            throw new InvalidNotificationCreationTimeException();
        }
    }
}