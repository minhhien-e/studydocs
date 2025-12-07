package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class InvalidTemplateUpdateTimeException extends DomainException {
    public InvalidTemplateUpdateTimeException() {
        super("Template update time cannot be null", DomainErrorCode.INVALID_TEMPLATE_UPDATE_TIME);
    }
}
