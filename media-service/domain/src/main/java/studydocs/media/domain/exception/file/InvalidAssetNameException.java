package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidAssetNameException extends DomainException {
    private InvalidAssetNameException(String message) {
        super(message, DomainErrorCode.FILE_NAME_INVALID);
    }

    public static InvalidAssetNameException nullOrEmpty() {
        return new InvalidAssetNameException("Asset name cannot be null or empty");
    }

    public static InvalidAssetNameException lengthExceeded(String assetName, int maxLength) {
        return new InvalidAssetNameException(String.format("Asset name '%s' must not exceed %d characters", assetName, maxLength));
    }
}
