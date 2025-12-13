package studydocs.notification.application.dto.query.notification;

import lombok.Builder;
import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

@Builder
public record CountUnreadQuery(UUID recipientId) implements Request<Integer> {
}
