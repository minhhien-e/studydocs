package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.notification.InvalidNotificationTypeException;

public record NotificationType(String value) {
    public NotificationType {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationTypeException();
        }
    }
}