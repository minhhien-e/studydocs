package studydocs.media.domain.exception.asset;

import studydocs.media.domain.enums.DomainErrorCode;
import studydocs.media.domain.exception.base.DomainException;

public class DeleteFailedException extends DomainException {
    public DeleteFailedException(String message) {
        super(message, DomainErrorCode.DELETE_FAILED);
    }
}
