package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidFileCreationTimeException extends DomainException {
    private InvalidFileCreationTimeException(String message) {
        super(message, DomainErrorCode.FILE_CREATION_TIME_INVALID);
    }

    public static InvalidFileCreationTimeException nullTime() {
        return new InvalidFileCreationTimeException("File creation time cannot be null");
    }
}
