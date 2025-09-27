package studydocs.notificationservice.application.service.mail;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.mail.EmailSenderPort;
import studydocs.notificationservice.application.port.render.TemplateRenderer;
import studydocs.notificationservice.application.usecase.email.SendEmailNotificationUseCase;
import studydocs.notificationservice.domain.model.entity.Template;
import studydocs.notificationservice.domain.model.event.SendMailEvent;
import studydocs.notificationservice.domain.model.valueobject.email.Email;
import studydocs.notificationservice.domain.model.valueobject.email.EmailContent;
import studydocs.notificationservice.domain.model.valueobject.email.EmailSubject;
import studydocs.notificationservice.domain.repository.TemplateRepositoryPort;

@Service
public class EmailNotificationService implements SendEmailNotificationUseCase {
    private final EmailSenderPort emailSenderPort;
    private final TemplateRepositoryPort templateRepository;
    private final TemplateRenderer templateRenderer;

    public EmailNotificationService(EmailSenderPort emailSenderPort,
                                    TemplateRepositoryPort templateRepository,
                                    @Qualifier("plainTextTemplateRenderer") TemplateRenderer templateRenderer) {
        this.emailSenderPort = emailSenderPort;
        this.templateRepository = templateRepository;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public void send(SendMailEvent event) {
        String to = event.email().value();
        Template notificationTemplate = templateRepository
                .getByName(event.templateName());
        EmailSubject subject = new EmailSubject(notificationTemplate.getSubjectTemplate().value());
        String content = notificationTemplate.getBodyTemplate().value();
        if (event.templateData() != null) {
            content = templateRenderer.render(content, event.templateData().data());
        }
        emailSenderPort.send(new Email(event.email(), subject, new EmailContent(content)));
    }

}
