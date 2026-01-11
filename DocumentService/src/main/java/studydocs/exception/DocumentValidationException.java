package studydocs.exception;

import lombok.Getter;

@Getter
public class DocumentValidationException extends RuntimeException {
    private final int errorCode = 400;
    public DocumentValidationException(String message) {
        super(message);
    }
}