package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingIdInRecipientException extends RequiredFieldMissingException {
    public MissingIdInRecipientException() {
        super("Id nhận thông báo");
    }
}
