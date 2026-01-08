package studydocs.media.domain.exception.file;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class FileNotSupportedException extends DomainException {
    public FileNotSupportedException(String extension) {
        super("File with extension " + extension + " is not supported", DomainErrorCode.FILE_NOT_SUPPORTED);
    }
}
