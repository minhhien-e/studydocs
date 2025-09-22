package studydocs.notificationservice.domain.exceptions.entity.notification;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class NotificationNotFoundException extends DomainException {
    public NotificationNotFoundException() {
        super("Thông báo không tồn tại", DomainErrorCode.NOTIFICATION_NOT_FOUND);
    }
}
