package studydocs.notificationservice.domain.factory.abstracts;

import studydocs.notificationservice.domain.model.entity.Template;

public interface TemplateFactory {
    Template create(String name, String channel, String subjectTemplate, String bodyTemplate, String description);
}
