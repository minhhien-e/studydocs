package studydocs.notification.application.dto.query.userprofile;

import studydocs.notification.application.dto.base.Query;
import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;

import java.util.UUID;

public record GetUserNotificationProfileQuery(
        UUID userId
) implements Query<UserNotificationProfileProjection> {
}
