package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class TemplateDescriptionNullOrEmptyException extends DomainException {
    public TemplateDescriptionNullOrEmptyException() {
        super("Template description cannot be null or empty", DomainErrorCode.TEMPLATE_DESCRIPTION_NULL_OR_EMPTY);
    }
}
