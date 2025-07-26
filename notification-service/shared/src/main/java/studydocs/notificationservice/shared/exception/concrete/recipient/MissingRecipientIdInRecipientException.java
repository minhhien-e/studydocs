package studydocs.notificationservice.shared.exception.concrete.recipient;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingRecipientIdInRecipientException extends RequiredFieldMissingException {
    public MissingRecipientIdInRecipientException() {
        super("Id người nhận thông báo");
    }
}
