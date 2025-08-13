package studydocs.notificationservice.infrastructure.inbound.rabbitmq.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;
import studydocs.notificationservice.application.dto.input.recipient.create.AddRecipientInput;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.template.TemplateOutput;
import studydocs.notificationservice.application.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.application.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByNameUseCase;
import studydocs.notificationservice.domain.event.UploadDocumentEvent;
import studydocs.notificationservice.shared.enums.NotificationChannel;
import studydocs.notificationservice.shared.enums.NotificationType;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UploadDocumentListener {

    private final GetTemplateByNameUseCase getTemplateByNameUseCase;
    private final AddNotificationUseCase addNotificationUseCase;
    private final AddRecipientUseCase addRecipientUseCase;

    @RabbitListener(queues = "queue.notification.push.document.upload")
    public void receive(UploadDocumentEvent event) {
        TemplateOutput templateOutputModel = getTemplateByNameUseCase
                .execute(new GetTemplateByNameInput(NotificationType.NEW_DOCUMENT.name()));
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("documentName", event.documentName());
        AddNotificationInput addNotificationInputModel = AddNotificationInput.builder()
                .senderId(event.userId())
                .recipientId(event.userId())
                .templateId(templateOutputModel.getId())
                .templateData(templateData)
                .chanel(NotificationChannel.PUSH.name())
                .type(NotificationType.NEW_DOCUMENT.name())
                .build();
        addNotificationUseCase.execute(addNotificationInputModel);
        AddRecipientInput recipient = new AddRecipientInput(event.userId(), event.userId());
        addRecipientUseCase.execute(recipient);
        //Push Thông báo
    }
}
