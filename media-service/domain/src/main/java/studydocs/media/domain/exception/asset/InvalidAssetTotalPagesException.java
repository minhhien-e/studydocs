package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class InvalidAssetTotalPagesException extends DomainException {
    private InvalidAssetTotalPagesException(String message) {
        super(message, DomainErrorCode.TOTAL_PAGES_INVALID);
    }

    public static InvalidAssetTotalPagesException negative(Integer pages) {
        return new InvalidAssetTotalPagesException(String.format("Total pages cannot be negative: %d", pages));
    }

    public static InvalidAssetTotalPagesException exceedsMax(Integer pages, int max) {
        return new InvalidAssetTotalPagesException(String.format("Total pages cannot exceed %d: %d", max, pages));
    }
}
