package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.notification.InvalidNotificationTypeException;

public class NotificationType extends ValueObject<NotificationType> {
    private final String value;

    public NotificationType(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationTypeException();
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