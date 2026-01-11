package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidAssetSizeException extends DomainException {
    private InvalidAssetSizeException(String message) {
        super(message, DomainErrorCode.FILE_SIZE_INVALID);
    }

    public static InvalidAssetSizeException nullSize() {
        return new InvalidAssetSizeException("Asset size cannot be null");
    }

    public static InvalidAssetSizeException lessThanMin(Long size, long min) {
        return new InvalidAssetSizeException(String.format("Asset size '%d' must be at least %d bytes", size, min));
    }

    public static InvalidAssetSizeException exceedsMax(Long size, long max) {
        return new InvalidAssetSizeException(String.format("Asset size '%d' must not exceed %d bytes", size, max));
    }
}
