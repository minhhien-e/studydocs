package studydocs.notificationservice.shared.exception.concrete.recipient.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingNotificationIdInRecipientException extends RequiredFieldMissingException {
    public MissingNotificationIdInRecipientException() {
        super("Id thông báo trong nhận thông báo");
    }
}
