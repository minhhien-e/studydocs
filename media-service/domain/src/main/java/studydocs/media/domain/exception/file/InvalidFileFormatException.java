package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidFileFormatException extends DomainException {
    public InvalidFileFormatException() {
        super("Invalid file format", DomainErrorCode.FILE_INVALID_FORMAT);
    }
}
