package studydocs.notificationservice.domain.valueobject.notification;

import studydocs.notificationservice.shared.enums.NotificationType;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.type.MissingNotificationTypeFieldException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.type.NotificationTypeNotFoundException;
import studydocs.notificationservice.shared.utils.StringUtils;

import java.util.Objects;

public class NotificationTypeValue {
    private final NotificationType value;

    public NotificationTypeValue(String value) {
        if (StringUtils.isNullOrBlank(value))
            throw new MissingNotificationTypeFieldException();
        try {
            this.value = NotificationType.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new NotificationTypeNotFoundException(value);
        }
    }

    public String getValue() {
        return value.name();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTypeValue that = (NotificationTypeValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
