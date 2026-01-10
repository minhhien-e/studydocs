package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidStorageLocationException extends DomainException {
    private InvalidStorageLocationException(String message) {
        super(message, DomainErrorCode.STORAGE_LOCATION_INVALID);
    }

    public static InvalidStorageLocationException nullOrEmptyKey() {
        return new InvalidStorageLocationException("Storage key cannot be null or empty");
    }

    public static InvalidStorageLocationException nullOrEmptyNamespace() {
        return new InvalidStorageLocationException("Storage namespace cannot be null or empty");
    }
}
