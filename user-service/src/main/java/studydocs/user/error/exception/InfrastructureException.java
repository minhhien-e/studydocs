package studydocs.user.error.exception;

import studydocs.user.error.ErrorCode;
import lombok.Getter;

@Getter
public class InfrastructureException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String method;

    public InfrastructureException(ErrorCode errorCode, String method) {
        super(); // message không cần
        this.errorCode = errorCode;
        this.method = method;
    }

    public InfrastructureException(ErrorCode errorCode, String method, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.method = method;
    }
}
