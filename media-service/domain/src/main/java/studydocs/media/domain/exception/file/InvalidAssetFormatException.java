package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidAssetFormatException extends DomainException {
    public InvalidAssetFormatException() {
        super("Invalid asset format", DomainErrorCode.FILE_INVALID_FORMAT);
    }
}
