package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingChanelInNotificationException extends RequiredFieldMissingException {
    public MissingChanelInNotificationException() {
        super("Loại kênh trong thông báo");
    }
}
