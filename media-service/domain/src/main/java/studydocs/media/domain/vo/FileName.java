package studydocs.media.domain.vo;

import io.github.domain.vo.ValueObject;
import studydocs.media.domain.exception.file.InvalidFileNameException;

public class FileName extends ValueObject<FileName> {
    private static final int MAX_LENGTH = 255;

    private final String value;

    private FileName(String value) {
        this.value = value;
    }

    public static FileName of(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw InvalidFileNameException.nullOrEmpty();
        }
        String trimmed = fileName.trim();
        if (trimmed.length() > MAX_LENGTH) {
            throw InvalidFileNameException.lengthExceeded(fileName, MAX_LENGTH);
        }
        return new FileName(trimmed);
    }

    public String getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{value};
    }

}

