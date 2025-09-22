package studydocs.notificationservice.domain.exceptions.entity.notification;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class NotificationNotDeletedException extends DomainException {
    public NotificationNotDeletedException() {
        super("Thông báo chưa bị xóa nên không thể khôi phục", DomainErrorCode.NOTIFICATION_NOT_DELETED);
    }
}
