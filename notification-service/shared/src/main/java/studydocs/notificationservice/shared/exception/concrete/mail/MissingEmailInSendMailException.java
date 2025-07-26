package studydocs.notificationservice.shared.exception.concrete.mail;

import studydocs.notificationservice.shared.exception.abstracts.RequiredFieldMissingException;

public class MissingEmailInSendMailException extends RequiredFieldMissingException {
    public MissingEmailInSendMailException() {
        super("Địa chỉ email khi gửi mail");
    }
}
