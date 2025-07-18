package studydocs.notificationservice.shared.exception.concrete.mail;

import studydocs.notificationservice.shared.exception.validation.RequiredFieldMissingException;

public class MissingEmailInSendMailException extends RequiredFieldMissingException {
    public MissingEmailInSendMailException() {
        super("Địa chỉ email khi gửi mail");
    }
}
