package studydocs.exception;

import lombok.Getter;

@Getter
public class DocumentProcessingException extends RuntimeException {
    private final int errorCode = 451;
    public DocumentProcessingException(String message) {
        super(message);
    }
    public DocumentProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}