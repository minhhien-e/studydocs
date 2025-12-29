package studydocs.notification.domain.exception.userprofile;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidEmailAddressException extends DomainException {
    public InvalidEmailAddressException(String reason) {
        super(
            "Invalid email address: " + reason, 
            DomainErrorCode.INVALID_EMAIL_ADDRESS
        );
    }
}
