package studydocs.notificationservice.domain.model.valueobject.email;

import studydocs.notificationservice.domain.exceptions.vo.email.EmailSubjectTooLongException;
import studydocs.notificationservice.domain.exceptions.vo.email.InvalidEmailSubjectFormatException;

public record EmailSubject(String value) {
    public EmailSubject {
        if (value.length() > 78) {
            throw new EmailSubjectTooLongException();
        }
        if (value.contains("\n") || value.contains("\r")) {
            throw new InvalidEmailSubjectFormatException(value);
        }
    }
}
