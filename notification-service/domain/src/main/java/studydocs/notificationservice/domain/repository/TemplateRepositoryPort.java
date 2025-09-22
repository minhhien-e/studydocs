package studydocs.notificationservice.domain.repository;

import studydocs.notificationservice.domain.model.entity.Template;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;

public interface TemplateRepositoryPort {
    void save(Template notificationTemplate);

    Template getByName(TemplateName name);

    void updateName(Template template);

    void updateSubject(Template template);

    void updateBody(Template template);

    void updateDescription(Template template);
}
