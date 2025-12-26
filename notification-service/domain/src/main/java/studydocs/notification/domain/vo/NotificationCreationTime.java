package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.notification.InvalidNotificationCreationTimeException;

import java.time.LocalDateTime;

public class NotificationCreationTime extends ValueObject<NotificationCreationTime> {
    private final LocalDateTime value;

    public NotificationCreationTime(LocalDateTime value) {
        if (value == null) {
            throw new InvalidNotificationCreationTimeException();
        }
        this.value = value;
    }

    public LocalDateTime value() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}