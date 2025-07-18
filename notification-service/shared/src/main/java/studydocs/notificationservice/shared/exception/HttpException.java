package studydocs.notificationservice.shared.exception;

import studydocs.notificationservice.shared.enums.ErrorCode;

public abstract class HttpException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;

    protected HttpException(int statusCode, ErrorCode errorCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode.name();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
