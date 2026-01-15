package studydocs.notification.application.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.dto.command.notification.CreateNotificationCommand;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.usecase.notification.CreateNotificationUseCasePort;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;

@Service
@RequiredArgsConstructor
public class CreateAndDistributeNotificationOrchestrator {
    private final CreateNotificationUseCasePort createNotificationUseCasePort;
    private final ReceiveNotificationUseCasePort receiveNotificationUseCasePort;

    public void handle(AddNotificationCommand command) {
        var createCommand = CreateNotificationCommand.builder()
                .senderId(command.senderId())
                .templateId(command.templateId())
                .bodyData(command.bodyData())
                .subjectData(command.subjectData())
                .type(command.type())
                .channel(command.channel())
                .build();
        var notificationId = createNotificationUseCasePort.execute(createCommand);

        command.recipients().forEach(recipient -> receiveNotificationUseCasePort.execute(
                ReceiveNotificationCommand.builder()
                        .notificationId(notificationId)
                        .recipientData(recipient)
                        .build()
        ));
    }
}
