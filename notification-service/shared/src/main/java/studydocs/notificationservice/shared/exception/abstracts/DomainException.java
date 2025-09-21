package studydocs.notificationservice.shared.exception.abstracts;

import studydocs.notificationservice.shared.enums.DomainErrorCode;

public class DomainException extends RuntimeException {
    private final DomainErrorCode errorCode;

    public DomainException(String message, DomainErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public DomainErrorCode getErrorCode() {
        return errorCode;
    }
}
