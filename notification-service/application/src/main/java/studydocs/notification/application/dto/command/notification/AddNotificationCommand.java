package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Builder
public record AddNotificationCommand(
        UUID senderId,
        UUID templateId,
        String channel,
        String category,
        Map<String, String> snapshotSubjectData,
        Map<String, String> snapshotBodyData,
        List<RecipientData> recipients
) implements Request<UUID> {
}
