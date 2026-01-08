package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

import java.util.UUID;

public class FileNotFoundException extends DomainException {
    public FileNotFoundException(UUID id) {
        super("File not found: " + id, DomainErrorCode.FILE_NOT_FOUND);
    }
}

