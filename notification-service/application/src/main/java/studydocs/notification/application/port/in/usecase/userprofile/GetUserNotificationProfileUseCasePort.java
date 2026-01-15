package studydocs.notification.application.port.in.usecase.userprofile;

import studydocs.notification.application.dto.projection.UserNotificationProfileProjection;
import studydocs.notification.application.dto.query.userprofile.GetUserNotificationProfileQuery;
import studydocs.notification.application.port.in.usecase.base.UseCase;

public interface GetUserNotificationProfileUseCasePort extends UseCase<UserNotificationProfileProjection, GetUserNotificationProfileQuery> {
}
