package studydocs.notificationservice.infrastructure.inbound.rabbitmq.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.dto.input.notification.create.AddNotificationInput;
import studydocs.notificationservice.application.dto.input.recipient.create.ReceiveNotificationInput;
import studydocs.notificationservice.application.dto.input.template.read.GetTemplateByNameInput;
import studydocs.notificationservice.application.dto.output.TemplateDto;
import studydocs.notificationservice.application.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.application.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.application.usecase.template.read.GetTemplateByNameUseCase;
import studydocs.notificationservice.domain.enums.NotificationCategoryEnum;
import studydocs.notificationservice.domain.enums.NotificationChannelEnum;
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
        TemplateDto templateOutputModel = getTemplateByNameUseCase
                .execute(new GetTemplateByNameInput(NotificationCategoryEnum.NEW_DOCUMENT.name()));
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("documentName", event.documentName());
        AddNotificationInput addNotificationInputModel = AddNotificationInput.builder()
                .senderId(event.userId())
                .recipientId(event.userId())
                .templateId(templateOutputModel.getId())
                .templateData(templateData)
                .chanel(NotificationChannelEnum.PUSH.name())
                .category(NotificationCategoryEnum.NEW_DOCUMENT.name())
                .build();
        addNotificationUseCase.execute(addNotificationInputModel);
        ReceiveNotificationInput recipient = new ReceiveNotificationInput(event.userId(), event.userId());
        addRecipientUseCase.execute(recipient);
        //Push Thông báo
    }
}
