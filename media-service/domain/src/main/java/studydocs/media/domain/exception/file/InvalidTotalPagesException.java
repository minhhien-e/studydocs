package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidTotalPagesException extends DomainException {
    private InvalidTotalPagesException(String message) {
        super(message, DomainErrorCode.TOTAL_PAGES_INVALID);
    }

    public static InvalidTotalPagesException negative(Integer pages) {
        return new InvalidTotalPagesException(String.format("Total pages cannot be negative: %d", pages));
    }

    public static InvalidTotalPagesException exceedsMax(Integer pages, int max) {
        return new InvalidTotalPagesException(String.format("Total pages cannot exceed %d: %d", max, pages));
    }
}
