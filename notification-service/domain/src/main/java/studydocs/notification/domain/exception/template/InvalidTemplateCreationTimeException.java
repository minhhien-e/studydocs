package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateCreationTimeException extends DomainException {
    public InvalidTemplateCreationTimeException() {
        super("Template creation time cannot be null", DomainErrorCode.INVALID_TEMPLATE_CREATION_TIME);
    }
}
