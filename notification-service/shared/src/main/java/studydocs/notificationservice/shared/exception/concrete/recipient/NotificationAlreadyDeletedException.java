package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.BusinessRuleViolationException;

public class NotificationAlreadyDeletedException extends BusinessRuleViolationException {
    public NotificationAlreadyDeletedException() {
        super("Thao tác không thành công do thông báo dã bị xóa");
    }
}
