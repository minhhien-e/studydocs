package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.ReceiveNotificationCommand;
import studydocs.notification.application.dto.payload.FileDataProviderPayload;
import studydocs.notification.application.dto.payload.UserDataProvidePayload;
import studydocs.notification.application.dto.payload.base.DataProvidePayload;
import studydocs.notification.application.enums.NotificationDataProviderPrefix;
import studydocs.notification.application.port.in.usecase.notification.ReceiveNotificationUseCasePort;
import studydocs.notification.application.service.builder.NotificationContentBuilder;
import studydocs.notification.application.service.builder.data.NotificationContent;
import studydocs.notification.application.service.support.TemplateVariableNormalizer;
import studydocs.notification.domain.aggregate.NotificationRecipient;
import studydocs.notification.domain.policy.NotificationSendPolicy;
import studydocs.notification.domain.repository.NotificationRecipientRepository;
import studydocs.notification.domain.repository.NotificationRepository;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReceiveNotificationUseCase implements ReceiveNotificationUseCasePort {
    private final NotificationRepository notificationRepository;
    private final NotificationRecipientRepository recipientRepository;
    private final NotificationSendPolicy notificationSendPolicy;
    private final NotificationContentBuilder notificationContentBuilder;
    private final TemplateVariableNormalizer normalizer;

    @Override
    public Void execute(ReceiveNotificationCommand params) {
        notificationSendPolicy.ensureCanReceive(params.notificationId(), params.recipientData().recipientId());

        var notification = notificationRepository.getById(params.notificationId());

        var notificationContent = new NotificationContent(notification.getSnapshotSubject().value(),
                notification.getSnapshotBody().value());

        var modelKeys = normalizer.extractVariables(notification.getSnapshotSubject().value() + notification.getSnapshotBody().value());

        for (var modelKey : modelKeys) {
            var payload = createPayload(params, modelKey);
            if (Objects.isNull(payload)) {
                continue;
            }
            notificationContent = notificationContentBuilder.build(notificationContent.subject(),
                    notificationContent.body(), payload);
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
        if (modelKey.startsWith(NotificationDataProviderPrefix.USER.getPrefix())) {
            return new UserDataProvidePayload(param.recipientData().recipientId());
        } else if (modelKey.startsWith(NotificationDataProviderPrefix.FILE.getPrefix())) {
            return new FileDataProviderPayload( UUID.fromString(param.recipientData().context().get("fileId").toString()));
        }
        return null;
    }
}
