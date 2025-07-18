package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingCreateAtInNotificationException extends RequiredFieldMissingException {
    public MissingCreateAtInNotificationException() {
        super("Thời gian tạo thông báo");
    }
}
