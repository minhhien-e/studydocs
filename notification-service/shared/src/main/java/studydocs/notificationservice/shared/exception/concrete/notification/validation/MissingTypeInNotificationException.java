package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingTypeInNotificationException extends RequiredFieldMissingException {
    public MissingTypeInNotificationException() {
        super("Loại thông báo");
    }
}
