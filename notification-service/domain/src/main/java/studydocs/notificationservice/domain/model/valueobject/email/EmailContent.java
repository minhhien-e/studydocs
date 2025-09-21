package studydocs.notificationservice.domain.model.valueobject.email;

import studydocs.notificationservice.domain.exceptions.vo.email.EmailContentTooLongException;

public record EmailContent(String value) {
    public EmailContent {
        if (value.length() > 900000) {
            throw new EmailContentTooLongException();
        }
    }
}
