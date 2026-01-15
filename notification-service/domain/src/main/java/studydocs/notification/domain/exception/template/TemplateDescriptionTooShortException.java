package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class TemplateDescriptionTooShortException extends DomainException {
    public TemplateDescriptionTooShortException() {
        super("Template description must be at least 10 characters", DomainErrorCode.TEMPLATE_DESCRIPTION_TOO_SHORT);
    }
}
