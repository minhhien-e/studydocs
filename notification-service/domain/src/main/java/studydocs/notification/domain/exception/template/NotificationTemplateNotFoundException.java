package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationTemplateNotFoundException extends DomainException {
    public NotificationTemplateNotFoundException(UUID templateId) {
        super("Notification template not found with id: " + templateId, DomainErrorCode.TEMPLATE_BY_ID_NOT_FOUND);
    }
    public NotificationTemplateNotFoundException(String name) {
        super("Notification template not found with name: " + name, DomainErrorCode.TEMPLATE_BY_NAME_NOT_FOUND);
    }
}
