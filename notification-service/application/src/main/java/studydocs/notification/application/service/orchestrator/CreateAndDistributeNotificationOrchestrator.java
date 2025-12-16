package studydocs.notification.application.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.dto.command.notification.CreateNotificationCommand;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.bus.MediatorBusPort;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateAndDistributeNotificationOrchestrator {
    private final MediatorBusPort bus;

    public void handle(AddNotificationCommand command) {
        var createCommand = new CreateNotificationCommand(
                command.senderId(),
                command.templateId(),
                command.channel(),
                command.category(),
                command.snapshotSubjectData(),
                command.snapshotBodyData()
        );
        var notificationId = bus.send(createCommand);

        command.recipients().forEach(recipient -> bus.send(
                ReceiveNotificationCommand.builder()
                        .notificationId(notificationId)
                        .recipientId(recipient.recipientId())
                        .subjectData(recipient.subjectData())
                        .bodyData(recipient.bodyData())
                        .build()
        ));
    }
}
