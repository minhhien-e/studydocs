package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidFileNameException extends DomainException {
    private InvalidFileNameException(String message) {
        super(message, DomainErrorCode.FILE_NAME_INVALID);
    }

    public static InvalidFileNameException nullOrEmpty() {
        return new InvalidFileNameException("File name cannot be null or empty");
    }

    public static InvalidFileNameException lengthExceeded(String fileName, int maxLength) {
        return new InvalidFileNameException(String.format("File name '%s' must not exceed %d characters", fileName, maxLength));
    }
}
