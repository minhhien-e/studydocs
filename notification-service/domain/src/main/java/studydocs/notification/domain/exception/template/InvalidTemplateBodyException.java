package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateBodyException extends DomainException {
    public InvalidTemplateBodyException() {
        super("Template body cannot be null or empty", DomainErrorCode.INVALID_TEMPLATE_BODY);
    }
}
