package studydocs.notification.infrastructure.exception.base;

import studydocs.notification.infrastructure.enums.InfrastructureErrorCode;

public abstract class InfrastructureException extends RuntimeException {
    private final InfrastructureErrorCode errorCode;

    public InfrastructureException(String message, InfrastructureErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InfrastructureErrorCode getErrorCode() {
        return errorCode;
    }

}
