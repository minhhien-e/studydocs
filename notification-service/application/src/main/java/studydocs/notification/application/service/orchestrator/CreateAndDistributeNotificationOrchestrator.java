package studydocs.notification.application.service.orchestrator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.AddNotificationCommand;
import studydocs.notification.application.dto.command.notification.CreateNotificationCommand;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.port.in.bus.MediatorBusPort;

@Service
@RequiredArgsConstructor
public class CreateAndDistributeNotificationOrchestrator {
    private final MediatorBusPort bus;

    public void handle(AddNotificationCommand command) {
        var createCommand = CreateNotificationCommand.builder()
                .senderId(command.senderId())
                .templateId(command.templateId())
                .snapshotBodyData(command.snapshotBodyData())
                .snapshotSubjectData(command.snapshotSubjectData())
                .type(command.type())
                .channel(command.channel())
                .build();
        var notificationId = bus.send(createCommand);

        command.recipients().forEach(recipient -> bus.send(
                ReceiveNotificationCommand.builder()
                        .notificationId(notificationId)
                        .recipientData(recipient)
                        .build()
        ));
    }
}
