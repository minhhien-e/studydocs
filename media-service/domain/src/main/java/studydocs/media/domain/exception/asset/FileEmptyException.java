package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class FileEmptyException extends DomainException {
    public FileEmptyException() {
        super("File cannot be empty", DomainErrorCode.FILE_EMPTY);
    }
}
