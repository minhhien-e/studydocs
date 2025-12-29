package studydocs.notification.domain.vo;

import io.github.domain.vo.ValueObject;

import java.time.LocalDateTime;

public class NotificationReceptionTime extends ValueObject<NotificationReceptionTime> {
    private final LocalDateTime value;

    public NotificationReceptionTime(LocalDateTime value) {
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
