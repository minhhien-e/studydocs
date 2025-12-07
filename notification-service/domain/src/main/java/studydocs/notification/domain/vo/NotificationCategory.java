package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.notification.InvalidNotificationCategoryException;

public record NotificationCategory(String value) {
    public NotificationCategory {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationCategoryException();
        }
    }
}