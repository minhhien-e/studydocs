package studydocs.media.domain.vo;

import io.github.domain.vo.ValueObject;

import studydocs.media.domain.exception.file.InvalidFileCreationTimeException;

import java.time.LocalDateTime;

public class FileCreationTime extends ValueObject<FileCreationTime> {
    private final LocalDateTime value;

    private FileCreationTime(LocalDateTime value) {
        if (value == null) {
            throw InvalidFileCreationTimeException.nullTime();
        }
        this.value = value;
    }

    public static FileCreationTime of(LocalDateTime time) {
        return new FileCreationTime(time);
    }

    public static FileCreationTime now() {
        return new FileCreationTime(LocalDateTime.now());
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    protected Object[] getEqualityComponents() {
        return new Object[]{
                value
        };
    }
}

