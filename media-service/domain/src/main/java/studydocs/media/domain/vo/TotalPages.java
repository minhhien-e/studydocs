package studydocs.media.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.media.domain.exception.file.InvalidTotalPagesException;

public class TotalPages extends ValueObject<TotalPages> {
    private static final int MAX_PAGES = 10000;
    private static final int MIN_PAGES = 0;

    private final Integer value;

    private TotalPages(Integer value) {
        this.value = value;
    }

    public static TotalPages of(Integer pages) {
        if (pages == null) {
            return new TotalPages(0);
        }
        if (pages < MIN_PAGES) {
            throw InvalidTotalPagesException.negative(pages);
        }
        if (pages > MAX_PAGES) {
            throw InvalidTotalPagesException.exceedsMax(pages, MAX_PAGES);
        }
        return new TotalPages(pages);
    }

    public Integer getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }
}

