package studydocs.notificationservice.domain.factory.impl;

import studydocs.notificationservice.domain.factory.abstracts.TemplateFactory;
import studydocs.notificationservice.domain.model.entity.Template;
import studydocs.notificationservice.domain.model.valueobject.channel.NotificationChannel;
import studydocs.notificationservice.domain.model.valueobject.name.TemplateName;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateBody;
import studydocs.notificationservice.domain.model.valueobject.template.TemplateSubject;

public class TemplateFactoryImpl implements TemplateFactory {
    @Override
    public Template create(String name, String channel, String subjectTemplate, String bodyTemplate, String description) {
        return new Template(new TemplateName(name), new NotificationChannel(channel), new TemplateSubject(subjectTemplate), new TemplateBody(bodyTemplate), description);
    }
}
