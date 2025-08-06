package studydocs.notificationservice.application.service.usecase.notification.read;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.read.GetNotificationByRecipientIdInputModel;
import studydocs.notificationservice.application.port.input.dto.outputmodel.notification.NotificationOutputModel;
import studydocs.notificationservice.application.port.input.dto.paging.SliceInput;
import studydocs.notificationservice.application.port.input.dto.paging.SliceOutput;
import studydocs.notificationservice.application.port.input.template.TemplateRenderer;
import studydocs.notificationservice.application.port.input.usecase.notificaton.read.GetNotificationByRecipientIdUseCase;
import studydocs.notificationservice.application.port.ouput.repository.NotificationRecipientRepositoryPort;
import studydocs.notificationservice.application.port.ouput.repository.NotificationTemplateRepositoryPort;
import studydocs.notificationservice.domain.entities.Notification;
import studydocs.notificationservice.domain.entities.NotificationRecipient;
import studydocs.notificationservice.domain.entities.NotificationTemplate;
import studydocs.notificationservice.shared.exception.concrete.notification.NotificationNotFoundException;
import studydocs.notificationservice.shared.exception.concrete.template.TemplateNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public SliceOutput<NotificationOutputModel> execute(SliceInput<GetNotificationByRecipientIdInputModel> inputModel) {
        var sliceOutput = findByRecipientId(inputModel);
        List<NotificationOutputModel> outputModels = new ArrayList<>();
        sliceOutput.content().forEach(recipient -> {
            Notification notification = recipient.getNotification();
            NotificationTemplate template = templateRepositoryPort.findById(notification.getTemplateId()).orElseThrow(() ->
                    new TemplateNotFoundException(notification.getTemplateId()));
            String content = createContent(notification, template);
            outputModels.add(createNotificationOutputModel(notification, template, recipient, content));
        });
        return new SliceOutput<>(outputModels, sliceOutput.hasNext());
    }

    private SliceOutput<NotificationRecipient> findByRecipientId(SliceInput<GetNotificationByRecipientIdInputModel> inputModel) {
        var request = inputModel.request();
        return recipientRepositoryPort.findByRecipientId(request.recipientId(),
                request.createdAt(),
                inputModel.limit());
    }

    private NotificationOutputModel createNotificationOutputModel(Notification notification, NotificationTemplate template, NotificationRecipient recipient, String content) {
        return NotificationOutputModel.builder()
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
        return templateRenderer.render(templateRenderer.render(template.getBodyTemplate().value(), notification.getTemplateData().data()), notification.getTemplateData().data());
    }
}
