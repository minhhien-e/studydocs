package studydocs.notification.domain.exception.userprofile;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class DuplicateFcmTokenException extends DomainException {
    public DuplicateFcmTokenException(String token) {
        super(
            "FCM token already exists: " + token, 
            DomainErrorCode.DUPLICATE_FCM_TOKEN
        );
    }
}
