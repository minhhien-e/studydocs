package studydocs.media.domain.vo;

import io.github.ddd.core.vo.ValueObject;
import studydocs.media.domain.exception.asset.InvalidAssetTotalPagesException;

public record TotalPages(Integer value) implements ValueObject {
    private static final int MAX_PAGES = 10000;
    private static final int MIN_PAGES = 0;

    public TotalPages {
        if (value == null) {
            value = 0;
        } else {
            if (value < MIN_PAGES) {
                throw InvalidAssetTotalPagesException.negative(value);
            }
            if (value > MAX_PAGES) {
                throw InvalidAssetTotalPagesException.exceedsMax(value, MAX_PAGES);
            }
        }
    }

    public static TotalPages of(Integer pages) {
        return new TotalPages(pages);
    }
}

