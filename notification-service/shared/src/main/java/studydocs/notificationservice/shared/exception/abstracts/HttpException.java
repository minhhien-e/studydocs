package studydocs.notificationservice.shared.exception.abstracts;

public abstract class HttpException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;

    protected HttpException(int statusCode, String errorCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
