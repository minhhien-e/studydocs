package studydocs.notificationservice.shared.exception.concrete.notification.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingTemplateIdInNotificationException extends RequiredFieldMissingException {
    public MissingTemplateIdInNotificationException() {
        super("ID mẫu thông báo trong thông báo");
    }
}
