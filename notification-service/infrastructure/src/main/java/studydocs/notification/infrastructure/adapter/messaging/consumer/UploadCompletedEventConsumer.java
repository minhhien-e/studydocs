package studydocs.notification.infrastructure.adapter.messaging.consumer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.dto.command.notification.RecipientData;
import studydocs.notification.application.dto.payload.UploadCompletedPayload;
import studydocs.notification.application.dto.query.template.GetTemplateByNameQuery;
import studydocs.notification.application.port.in.usecase.template.GetTemplateByNameUseCasePort;
import studydocs.notification.application.service.orchestrator.CreateAndDistributeNotificationOrchestrator;
import studydocs.notification.infrastructure.config.RabbitMQConfig;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UploadCompletedEventConsumer {
    private final CreateAndDistributeNotificationOrchestrator createAndDistributeNotificationOrchestrator;
    private final GetTemplateByNameUseCasePort getTemplateByNameUseCasePort;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.UPLOAD_COMPLETED_NOTIFICATION_QUEUE)
    public void handleNotificationReceived(UploadCompletedPayload payload) {
        String templateName = "UPLOAD_COMPLETED";
        var template = getTemplateByNameUseCasePort.execute(new GetTemplateByNameQuery(templateName));
        Map<String, Object> payloadMap = objectMapper.convertValue(payload, new TypeReference<>() {
        });
        var command = AddNotificationCommand.builder()
                .senderId(payload.userId())
                .templateId(template.id())
                .channel(template.channel())
                .type(template.type())
                .recipients(List.of(RecipientData.builder()
                                .recipientId(payload.userId())
                                .context(payloadMap)
                        .build()))
                .build();

        createAndDistributeNotificationOrchestrator.handle(command);
    }
}
