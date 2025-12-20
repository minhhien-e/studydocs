package studydocs.notification.application.service.usecase.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.notification.application.dto.command.notification.SendNotificationCommand;
import studydocs.notification.application.dto.payload.NotificationSendPayload;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.port.in.usecase.notification.SendNotificationUseCasePort;
import studydocs.notification.application.port.out.messaging.NotificationSenderPort;
import studydocs.notification.application.port.out.repository.NotificationQueries;
import studydocs.notification.application.port.out.repository.NotificationRecipientQueries;
import studydocs.notification.application.port.out.repository.UserNotificationProfileQueries;
import studydocs.notification.domain.enums.NotificationChannel;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SendNotificationUseCase implements SendNotificationUseCasePort {
    private final NotificationRecipientQueries notificationRecipientQueries;
    private final NotificationQueries notificationQueries;
    private final UserNotificationProfileQueries userNotificationProfileQueries;
    private final Map<String, NotificationSenderPort> senders;

    public Void execute(SendNotificationCommand command) {
        var recipient = notificationRecipientQueries.getById(command.recipientId());
        var notification = notificationQueries.getById(command.notificationId());
        var profile = userNotificationProfileQueries.getByUserId(command.recipientId());
        NotificationChannel channel = NotificationChannel.valueOf(notification.getChannel());
        var destinations = destinations(channel, profile);
        var payload = new NotificationSendPayload(recipient.getRenderedSubject(), recipient.getRenderedBody(), destinations);
        senders.get(notification.getChannel()).send(payload);
        return null;
    }

    private List<String> destinations(NotificationChannel channel, UserNotificationProfileProjection profile) {
        return switch (channel) {
            case EMAIL -> List.of(profile.emailAddress());
            case SMS -> List.of(profile.phoneNumber());
            case PUSH -> profile.fcmTokens();
        };
    }
}
