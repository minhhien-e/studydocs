package studydocs.notificationservice.domain.exceptions.entity.notification;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class NotificationAlreadyDeletedException extends DomainException {
    private static final String MESSAGE = "Thông báo đã bị xóa trước đó. Không thể thực hiện thao tác này.";

    public NotificationAlreadyDeletedException() {
        super(MESSAGE, DomainErrorCode.NOTIFICATION_ALREADY_DELETED);
    }
}
