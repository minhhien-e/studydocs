package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

public class NotificationTemplateAlreadyExistsException extends DomainException {
    public NotificationTemplateAlreadyExistsException(String templateName) {
        super("Notification template already exists with name: " + templateName, DomainErrorCode.TEMPLATE_ALREADY_EXISTS);
    }
}
