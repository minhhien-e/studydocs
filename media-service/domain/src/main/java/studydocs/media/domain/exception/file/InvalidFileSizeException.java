package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidFileSizeException extends DomainException {
    private InvalidFileSizeException(String message) {
        super(message, DomainErrorCode.FILE_SIZE_INVALID);
    }

    public static InvalidFileSizeException nullSize() {
        return new InvalidFileSizeException("File size cannot be null");
    }

    public static InvalidFileSizeException lessThanMin(Long size, long min) {
        return new InvalidFileSizeException(String.format("File size '%d' must be at least %d bytes", size, min));
    }

    public static InvalidFileSizeException exceedsMax(Long size, long max) {
        return new InvalidFileSizeException(String.format("File size '%d' must not exceed %d bytes", size, max));
    }
}
