package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class UploadFailedException extends DomainException {
    public UploadFailedException(String message) {
        super(message, DomainErrorCode.UPLOAD_FAILED);
    }
}
