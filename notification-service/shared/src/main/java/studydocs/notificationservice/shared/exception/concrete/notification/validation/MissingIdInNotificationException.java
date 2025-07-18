package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingIdInNotificationException extends RequiredFieldMissingException {

    public MissingIdInNotificationException() {
        super("ID thông báo");
    }
}
