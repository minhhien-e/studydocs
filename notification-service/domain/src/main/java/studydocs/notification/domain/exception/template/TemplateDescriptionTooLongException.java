package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class TemplateDescriptionTooLongException extends DomainException {
    public TemplateDescriptionTooLongException() {
        super("Template description cannot exceed 500 characters", DomainErrorCode.TEMPLATE_DESCRIPTION_TOO_LONG);
    }
}
