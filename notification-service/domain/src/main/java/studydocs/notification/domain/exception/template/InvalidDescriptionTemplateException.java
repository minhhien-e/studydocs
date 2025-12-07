package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidDescriptionTemplateException extends DomainException {
    public InvalidDescriptionTemplateException() {
      super("Template description is invalid or malformed.", DomainErrorCode.INVALID_TEMPLATE_DESCRIPTION);
    }
}
