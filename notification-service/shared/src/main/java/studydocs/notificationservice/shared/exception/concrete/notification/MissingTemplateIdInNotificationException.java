package studydocs.notificationservice.shared.exception.concrete.notification;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingTemplateIdInNotificationException extends RequiredFieldMissingException {
    public MissingTemplateIdInNotificationException() {
        super("ID mẫu thông báo trong thông báo");
    }
}
