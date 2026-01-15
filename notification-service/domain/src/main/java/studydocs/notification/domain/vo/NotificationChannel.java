package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.notification.InvalidNotificationChannelException;

public class NotificationChannel extends ValueObject<NotificationChannel> {
    private final String value;

    public NotificationChannel(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationChannelException();
        }
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}
