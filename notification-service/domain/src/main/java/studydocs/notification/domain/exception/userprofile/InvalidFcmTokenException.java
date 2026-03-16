package studydocs.notification.domain.exception.userprofile;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidFcmTokenException extends DomainException {
    public InvalidFcmTokenException(String reason) {
        super(
            "Invalid FCM token: " + reason, 
            DomainErrorCode.INVALID_FCM_TOKEN
        );
    }
}
