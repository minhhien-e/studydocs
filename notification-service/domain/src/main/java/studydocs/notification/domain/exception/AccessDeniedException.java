package studydocs.notification.domain.exception;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class AccessDeniedException extends DomainException {
    public AccessDeniedException(UUID userId, UUID targetId) {
        super("User " + userId + " is not allowed to access resource " + targetId, DomainErrorCode.ACCESS_DENIED);

    }
}
