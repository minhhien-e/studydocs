package studydocs.notificationservice.domain.model.valueobject.email;

import studydocs.notificationservice.domain.exceptions.vo.email.InvalidEmailFormatException;

public record EmailAddress(String value) {
    public EmailAddress(String value) {
        String regex = "^[\\w.+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!value.matches(regex)) {
            throw new InvalidEmailFormatException(value);
        }
        this.value = value;
    }
}
