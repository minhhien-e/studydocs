package studydocs.notificationservice.application.service.usecase.notification.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notificationservice.application.dto.input.notification.read.GetNotificationByRecipientIdInput;
import studydocs.notificationservice.application.dto.output.notification.NotificationOutput;
import studydocs.notificationservice.application.port.render.TemplateRenderer;
import studydocs.notificationservice.application.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.domain.entity.Notification;
import studydocs.notificationservice.domain.entity.NotificationRecipient;
import studydocs.notificationservice.domain.entity.NotificationTemplate;
import studydocs.notificationservice.domain.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.domain.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;
import studydocs.notificationservice.shared.paging.SliceInput;
import studydocs.notificationservice.shared.paging.SliceOutput;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetNotificationByRecipientIdUseCaseImpl implements GetNotificationByRecipientIdUseCase {
    private final NotificationRecipientRepositoryPort recipientRepositoryPort;
    private final NotificationTemplateRepositoryPort templateRepositoryPort;
    private final TemplateRenderer templateRenderer;

    public GetNotificationByRecipientIdUseCaseImpl(NotificationRecipientRepositoryPort recipientRepositoryPort,
                                                   NotificationTemplateRepositoryPort templateRepositoryPort,
                                                   @Qualifier("plainTextTemplateRenderer") TemplateRenderer templateRenderer) {
        this.recipientRepositoryPort = recipientRepositoryPort;
        this.templateRepositoryPort = templateRepositoryPort;
        this.templateRenderer = templateRenderer;
    }

    @Override
    public SliceOutput<NotificationOutput> execute(SliceInput<GetNotificationByRecipientIdInput> inputModel) {
        var sliceOutput = findByRecipientId(inputModel);
        List<NotificationOutput> outputModels = new ArrayList<>();
        sliceOutput.content().forEach(recipient -> {
            Notification notification = recipient.getNotification();
            NotificationTemplate template = templateRepositoryPort.findById(notification.getTemplateId()).orElseThrow(() ->
                    new TemplateNotFoundException(notification.getTemplateId()));
            String content = createContent(notification, template);
            outputModels.add(createNotificationOutputModel(notification, template, recipient, content));
        });
        return new SliceOutput<>(outputModels, sliceOutput.hasNext());
    }

    private SliceOutput<NotificationRecipient> findByRecipientId(SliceInput<GetNotificationByRecipientIdInput> inputModel) {
        var request = inputModel.request();
        return recipientRepositoryPort.findByRecipientId(request.recipientId(),
                request.createdAt(),
                inputModel.limit());
    }

    private NotificationOutput createNotificationOutputModel(Notification notification, NotificationTemplate template, NotificationRecipient recipient, String content) {
        return NotificationOutput.builder()
                .id(notification.getId())
                .senderId(notification.getSenderId())
                .recipientId(recipient.getRecipientId())
                .isRead(recipient.isRead())
                .type(notification.getType().getValue())
                .subject(template.getSubjectTemplate().value())
                .content(content)
                .createdAt(notification.getCreateAt().getValue())
                .build();
    }

    private String createContent(Notification notification, NotificationTemplate template) {
        return templateRenderer.render(template.getBodyTemplate().value(), notification.getTemplateData().data());
    }
}
