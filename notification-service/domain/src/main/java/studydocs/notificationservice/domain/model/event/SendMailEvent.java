package studydocs.notificationservice.domain.model.event;


import studydocs.notificationservice.domain.model.valueobject.TemplateData;
import studydocs.notificationservice.domain.model.valueobject.email.EmailAddress;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;

import java.util.UUID;

public record SendMailEvent(UUID userId, EmailAddress email, TemplateData templateData,
                            TemplateName templateName) {

}
