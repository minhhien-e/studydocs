package studydocs.notificationservice.shared.exception.concrete.notification;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingIdInNotificationException extends RequiredFieldMissingException {

    public MissingIdInNotificationException() {
        super("ID thông báo");
    }
}
