package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidAssetCreationTimeException extends DomainException {
    private InvalidAssetCreationTimeException(String message) {
        super(message, DomainErrorCode.FILE_CREATION_TIME_INVALID);
    }

    public static InvalidAssetCreationTimeException nullTime() {
        return new InvalidAssetCreationTimeException("Asset creation time cannot be null");
    }
}
