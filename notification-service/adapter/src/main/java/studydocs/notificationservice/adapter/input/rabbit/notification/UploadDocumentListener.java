package studydocs.notificationservice.adapter.input.rabbit.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notificationservice.application.port.input.dto.inputmodel.notification.create.AddNotificationInputModel;
import studydocs.notificationservice.application.port.input.dto.inputmodel.recipient.create.AddRecipientInputModel;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.read.GetTemplateByNameInputModel;
import studydocs.notificationservice.application.port.input.dto.outputmodel.template.TemplateOutputModel;
import studydocs.notificationservice.application.port.input.usecase.notificaton.create.AddNotificationUseCase;
import studydocs.notificationservice.application.port.input.usecase.recipient.create.AddRecipientUseCase;
import studydocs.notificationservice.application.port.input.usecase.template.read.GetTemplateByNameUseCase;
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
        TemplateOutputModel templateOutputModel = getTemplateByNameUseCase
                .execute(new GetTemplateByNameInputModel(NotificationType.NEW_DOCUMENT.name()));
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("documentName", event.documentName());
        AddNotificationInputModel addNotificationInputModel = AddNotificationInputModel.builder()
                .senderId(event.userId())
                .recipientId(event.userId())
                .templateId(templateOutputModel.getId())
                .templateData(templateData)
                .chanel(NotificationChannel.PUSH.name())
                .type(NotificationType.NEW_DOCUMENT.name())
                .build();
        addNotificationUseCase.execute(addNotificationInputModel);
        AddRecipientInputModel recipient = new AddRecipientInputModel(event.userId(), event.userId());
        addRecipientUseCase.execute(recipient);
        //Push Thông báo
    }
}
