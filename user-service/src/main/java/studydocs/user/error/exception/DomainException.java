package studydocs.user.error.exception;

import studydocs.user.error.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String methodName;

    public DomainException(ErrorCode errorCode, String methodName) {
        super();
        this.errorCode = errorCode;
        this.methodName = methodName;
    }
}
