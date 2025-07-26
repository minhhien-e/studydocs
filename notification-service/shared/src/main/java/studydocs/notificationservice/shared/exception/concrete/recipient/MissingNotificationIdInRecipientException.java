package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingNotificationIdInRecipientException extends RequiredFieldMissingException {
    public MissingNotificationIdInRecipientException() {
        super("Id thông báo trong nhận thông báo");
    }
}
