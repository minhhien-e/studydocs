package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateDataException extends DomainException {
    public InvalidTemplateDataException() {
        super("Template data cannot be null", DomainErrorCode.INVALID_TEMPLATE_DATA);
    }
}
