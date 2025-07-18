package studydocs.notificationservice.shared.exception.concrete.recipient.validation;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingRecipientIdInRecipientException extends RequiredFieldMissingException {
    public MissingRecipientIdInRecipientException() {
        super("Id người nhận thông báo");
    }
}
