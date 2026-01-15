package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidAssetContentTypeException extends DomainException {
    private InvalidAssetContentTypeException(String message) {
        super(message, DomainErrorCode.FILE_INVALID_FORMAT);
    }

    public static InvalidAssetContentTypeException nullOrEmpty() {
        return new InvalidAssetContentTypeException("Asset content type cannot be null or empty");
    }
}
