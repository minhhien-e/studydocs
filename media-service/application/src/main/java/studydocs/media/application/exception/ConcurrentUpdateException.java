package studydocs.media.application.exception;

import studydocs.media.domain.enums.DomainErrorCode;

public class ConcurrentUpdateException extends ApplicationDomainException {
    public ConcurrentUpdateException(String message) {
        super(message, DomainErrorCode.CONCURRENT_UPDATE.getValue(),
                DomainErrorCode.CONCURRENT_UPDATE.getCategory().name());
    }
}
