package studydocs.notification.application.dto.query.notification;


import studydocs.notification.application.dto.base.Request;

import java.util.UUID;

public record CountUnreadQuery(UUID recipientId) implements Request<Integer> {
}
