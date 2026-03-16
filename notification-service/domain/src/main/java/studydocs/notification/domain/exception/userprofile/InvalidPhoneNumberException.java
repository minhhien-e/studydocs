package studydocs.notification.domain.exception.userprofile;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidPhoneNumberException extends DomainException {
    public InvalidPhoneNumberException(String reason) {
        super(
            "Invalid phone number: " + reason, 
            DomainErrorCode.INVALID_PHONE_NUMBER
        );
    }
}
