package studydocs.notification.domain.vo;

import studydocs.notification.domain.exception.notification.InvalidNotificationChannelException;

public record NotificationChannel (String value){
    public NotificationChannel {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationChannelException();
        }
    }
}
