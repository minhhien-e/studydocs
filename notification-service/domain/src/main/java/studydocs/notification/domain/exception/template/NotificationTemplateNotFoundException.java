package studydocs.notification.domain.exception.template;

import studydocs.notification.domain.enums.DomainErrorCode;
import studydocs.notification.domain.exception.base.DomainException;

import java.util.UUID;

public class NotificationTemplateNotFoundException extends DomainException {
    public NotificationTemplateNotFoundException(UUID templateId) {
        super("Notification template not found with id: " + templateId, DomainErrorCode.TEMPLATE_NOT_FOUND);
    }
}
