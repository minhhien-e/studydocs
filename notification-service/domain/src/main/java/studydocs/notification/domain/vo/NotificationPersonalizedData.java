package studydocs.notification.domain.vo;

import java.util.Map;

import studydocs.notification.domain.exception.notification.InvalidNotificationPersonalizedDataException;

public record NotificationPersonalizedData(Map<String, String> value) {
    public NotificationPersonalizedData {
        if (value == null) {
            throw new InvalidNotificationPersonalizedDataException();
        }
    }
}
