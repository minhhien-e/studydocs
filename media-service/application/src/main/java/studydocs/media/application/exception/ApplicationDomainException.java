package studydocs.media.application.exception;

import lombok.Getter;

@Getter
public class ApplicationDomainException extends RuntimeException {
    private final Integer code;
    private final String category;

    public ApplicationDomainException(String message, Integer code, String category) {
        super(message);
        this.code = code;
        this.category = category;
    }
}
