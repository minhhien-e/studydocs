package studydocs.media.shared.web;

public class HttpException extends RuntimeException {
    private final int statusCode;
    private final Integer errorCode;

    public HttpException(String message, int statusCode, Integer errorCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
