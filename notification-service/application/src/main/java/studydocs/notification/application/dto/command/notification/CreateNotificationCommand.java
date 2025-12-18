package studydocs.notification.application.dto.command.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.Map;
import java.util.UUID;
@Builder
public record CreateNotificationCommand(UUID senderId,
                                        UUID templateId,
                                        String channel,
                                        String type,
                                        Map<String, String> snapshotSubjectData,
                                        Map<String, String> snapshotBodyData) implements Request<UUID> {
}
