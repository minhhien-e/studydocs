package studydocs.notificationservice.infrastructure.inbound.rabbitmq.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;
import studydocs.notificationservice.application.dto.input.recipient.create.AddRecipientInput;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.TemplateOutput;
import studydocs.notificationservice.application.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.application.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByNameUseCase;
import studydocs.notificationservice.domain.enums.NotificationCategoryEnum;
import studydocs.notificationservice.domain.enums.NotificationChannel;
import studydocs.notificationservice.domain.model.event.UploadDocumentEvent;

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
                .execute(new GetTemplateByNameInput(NotificationCategoryEnum.NEW_DOCUMENT.name()));
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("documentName", event.documentName());
        AddNotificationInput addNotificationInputModel = AddNotificationInput.builder()
                .senderId(event.userId())
                .recipientId(event.userId())
                .templateId(templateOutputModel.getId())
                .templateData(templateData)
                .chanel(NotificationChannel.PUSH.name())
                .type(NotificationCategoryEnum.NEW_DOCUMENT.name())
                .build();
        addNotificationUseCase.execute(addNotificationInputModel);
        AddRecipientInput recipient = new AddRecipientInput(event.userId(), event.userId());
        addRecipientUseCase.execute(recipient);
        //Push Thông báo
    }
}
