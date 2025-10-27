package studydocs.exception;

import lombok.Getter;

@Getter
public class DocumentValidationException extends RuntimeException {
    private final int errorCode;

    public DocumentValidationException(int errorCode) {
        super("Document không tồn tại hoặc không hợp lệ");
        this.errorCode = errorCode;
    }

}