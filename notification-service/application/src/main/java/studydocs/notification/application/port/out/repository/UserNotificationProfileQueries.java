package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;

import java.util.UUID;

public interface UserNotificationProfileQueries {
    UserNotificationProfileProjection getByUserId(UUID userId);
}
