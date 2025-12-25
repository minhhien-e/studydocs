package studydocs.notification.domain.exception.userprofile;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class UserNotificationProfileAlreadyExistsException extends DomainException {
    public UserNotificationProfileAlreadyExistsException(UUID userId) {
        super("User Notification Profile already exists with userId: " + userId, DomainErrorCode.USER_NOTIFICATION_PROFILE_ALREADY_EXISTS);
    }
}
