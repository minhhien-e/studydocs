package studydocs.notification.domain.exception.userprofile;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class UserNotificationProfileNotFoundException extends DomainException {
    public UserNotificationProfileNotFoundException(UUID userId) {
        super(
            "User notification profile not found for userId: " + userId, 
            DomainErrorCode.USER_NOTIFICATION_PROFILE_NOT_FOUND
        );
    }
}
