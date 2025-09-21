package studydocs.notificationservice.shared.exception.abstracts;

import studydocs.notificationservice.shared.enums.InfrastructureErrorCode;

public class InfrastructureException extends RuntimeException {
    private final InfrastructureErrorCode errorCode;

    public InfrastructureException(String message, InfrastructureErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InfrastructureErrorCode getErrorCode() {
        return errorCode;
    }
}
