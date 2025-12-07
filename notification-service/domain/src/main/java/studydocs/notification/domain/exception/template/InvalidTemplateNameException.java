package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateNameException extends DomainException {
    public InvalidTemplateNameException() {
        super("Template name cannot be null or empty", DomainErrorCode.INVALID_TEMPLATE_NAME);
    }
}
