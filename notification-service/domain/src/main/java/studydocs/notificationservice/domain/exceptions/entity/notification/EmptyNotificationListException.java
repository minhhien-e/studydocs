package studydocs.notificationservice.domain.exceptions.entity.notification;

import studydocs.notificationservice.shared.enums.DomainErrorCode;
import studydocs.notificationservice.shared.exception.abstracts.DomainException;

public class EmptyNotificationListException extends DomainException {
    private final static String MESSAGE = "Người dùng không có thông báo %s";

    public EmptyNotificationListException(String type) {
        super(String.format(MESSAGE, type), DomainErrorCode.EMPTY_NOTIFICATION_LIST);
    }
}
