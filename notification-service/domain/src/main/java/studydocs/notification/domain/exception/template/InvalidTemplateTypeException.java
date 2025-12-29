package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateTypeException extends DomainException {
    public InvalidTemplateTypeException() {
        super("Invalid template type", DomainErrorCode.INVALID_TEMPLATE_TYPE);
    }
}
