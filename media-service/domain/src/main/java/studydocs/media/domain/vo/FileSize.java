package studydocs.media.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.media.domain.exception.file.InvalidFileSizeException;

public class FileSize extends ValueObject<FileSize> {
    private static final long MAX_SIZE = 50 * 1024 * 1024; // 50MB
    private static final long MIN_SIZE = 1; // 1 byte

    private final Long value;

    private FileSize(Long value) {
        this.value = value;
    }

    public static FileSize of(Long size) {
        if (size == null) {
            throw InvalidFileSizeException.nullSize();
        }
        if (size < MIN_SIZE) {
            throw InvalidFileSizeException.lessThanMin(size, MIN_SIZE);
        }
        if (size > MAX_SIZE) {
            throw InvalidFileSizeException.exceedsMax(size, MAX_SIZE);
        }
        return new FileSize(size);
    }

    public Long getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }

}

