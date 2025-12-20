package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.dto.payload.UserDataProvidePayload;
import studydocs.notification.application.dto.payload.base.DataProvidePayload;
import studydocs.notification.application.port.in.renderer.TemplateRenderer;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;
import studydocs.notification.application.service.builder.NotificationContentBuilder;
import studydocs.notification.application.service.builder.data.NotificationContent;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReceiveNotificationUseCase implements ReceiveNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final NotificationContentBuilder notificationContentBuilder;
    private final TemplateRenderer renderer;

    @Override
    public Void execute(ReceiveNotificationCommand params) {
        notificationSendPolicy.ensureCanSend(params.recipientData().recipientId());

        var notification = notificationRepository.getById(params.notificationId());

        var modelKeys = renderer.getModelKeys(notification.getSnapshotSubject().value() + notification.getSnapshotBody().value());

        var notificationContent = new NotificationContent(notification.getSnapshotSubject().value(),
                notification.getSnapshotBody().value());

        for (var modelKey : modelKeys) {
            notificationContent = notificationContentBuilder.build(notificationContent.subject(),
                    notificationContent.body(), Objects.requireNonNull(createPayload(params, modelKey)));
        }

        var recipient = NotificationRecipient.create(
                params.notificationId(),
                params.recipientData().recipientId(),
                notificationContent.subject(),
                notificationContent.body()
        );
        recipientRepository.save(recipient);
        return null;
    }

    private DataProvidePayload createPayload(ReceiveNotificationCommand param, String modelKey) {
        if (modelKey.startsWith("user.")) {
            return new UserDataProvidePayload(param.recipientData().recipientId());
        }
        return null;
    }
}
