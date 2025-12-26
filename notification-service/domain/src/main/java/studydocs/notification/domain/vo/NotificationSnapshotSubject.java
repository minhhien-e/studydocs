package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.notification.domain.exception.notification.InvalidNotificationSnapshotSubjectException;

public class NotificationSnapshotSubject extends ValueObject<NotificationSnapshotSubject> {
    private final String value;

    public NotificationSnapshotSubject(String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidNotificationSnapshotSubjectException();
        }
        if (value.length() > 500) {
            throw new InvalidNotificationSnapshotSubjectException("Subject cannot exceed 500 characters");
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
