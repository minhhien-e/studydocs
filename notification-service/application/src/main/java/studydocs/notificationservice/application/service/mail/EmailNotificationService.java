package studydocs.notificationservice.application.service.mail;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.email.SendEmailNotificationUseCase;
import studydocs.notificationservice.application.port.input.template.TemplateRenderer;
import studydocs.notificationservice.application.port.ouput.mail.EmailSenderPort;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.domain.event.SendMailEvent;
import studydocs.notificationservice.domain.valueobject.EmailData;
import studydocs.notificationservice.shared.exception.concrete.mail.MissingEmailInSendMailException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.name.MissingNameFieldException;
import studydocs.notificationservice.shared.utils.StringUtils;

@Service
public class EmailNotificationService implements SendEmailNotificationUseCase {
    private final EmailSenderPort emailSenderPort;
    private final NotificationTemplateRepositoryPort templateRepository;
    private final TemplateRenderer templateRenderer;

    public EmailNotificationService(EmailSenderPort emailSenderPort,
                                    NotificationTemplateRepositoryPort templateRepository,
                                    @Qualifier("plainTextTemplateRenderer") TemplateRenderer templateRenderer) {
        this.emailSenderPort = emailSenderPort;
        this.templateRepository = templateRepository;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public void send(SendMailEvent event) {
        validate(event);
        String to = event.email();
        String templateName = event.templateName();

        NotificationTemplate notificationTemplate = templateRepository
                .findByName(templateName)
                .orElseThrow(() -> new TemplateNotFoundException(templateName));
        String subject = notificationTemplate.getSubjectTemplate().value();
        String content = notificationTemplate.getBodyTemplate().value();
        if (event.templateData() != null) {
            content = templateRenderer.render(content, event.templateData());
        }
        emailSenderPort.send(new EmailData(to, subject, content));
    }

    private void validate(SendMailEvent event) {
        if (StringUtils.isNullOrBlank(event.email())) {
            throw new MissingEmailInSendMailException();
        }
        if (StringUtils.isNullOrBlank(event.templateName())) {
            throw new MissingNameFieldException("mẫu thông báo khi gửi mail");
        }
    }

}
