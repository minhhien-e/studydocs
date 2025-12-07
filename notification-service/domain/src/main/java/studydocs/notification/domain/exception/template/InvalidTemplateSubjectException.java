package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateSubjectException extends DomainException {
    public InvalidTemplateSubjectException() {
        super("Template subject cannot be null or empty", DomainErrorCode.INVALID_TEMPLATE_SUBJECT);
    }
}
