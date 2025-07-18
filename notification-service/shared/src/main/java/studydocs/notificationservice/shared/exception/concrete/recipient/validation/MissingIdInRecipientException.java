package studydocs.notificationservice.shared.exception.concrete.recipient.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingIdInRecipientException extends RequiredFieldMissingException {
    public MissingIdInRecipientException() {
        super("Id nhận thông báo");
    }
}
