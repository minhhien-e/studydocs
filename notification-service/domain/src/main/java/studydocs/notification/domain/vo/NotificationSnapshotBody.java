package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.notification.InvalidNotificationSnapshotBodyException;

public class NotificationSnapshotBody extends ValueObject<NotificationSnapshotBody> {
    private final String value;

    public NotificationSnapshotBody(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationSnapshotBodyException();
        }
        if (value.length() > 10000) {
            throw new InvalidNotificationSnapshotBodyException("Body cannot exceed 10000 characters");
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
