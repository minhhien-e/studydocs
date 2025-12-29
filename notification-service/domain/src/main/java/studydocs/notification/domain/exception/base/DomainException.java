package studydocs.notification.domain.exception.base;

import studydocs.notification.domain.enums.DomainErrorCode;

public abstract class DomainException extends RuntimeException {
    private final DomainErrorCode errorCode;

    public DomainException(String message, DomainErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainErrorCode getErrorCode() {
        return errorCode;
    }

}
