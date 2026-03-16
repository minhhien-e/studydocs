package studydocs.exception;

import lombok.Getter;

@Getter
public class DocumentValidationException extends RuntimeException {

    private final int errorCode = 503;

    public DocumentValidationException() {
        super("Document validation failed");
    }
}
