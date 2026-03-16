package studydocs.notification.application.port.in.usecase.notification;

import studydocs.notification.application.dto.projection.NotificationMetadataProjection;
import studydocs.notification.application.dto.query.notification.GetNotificationMetadataQuery;
import studydocs.notification.application.port.in.usecase.base.UseCase;

import java.util.List;

public interface GetNotificationMetadataUseCasePort extends UseCase<List<NotificationMetadataProjection>, GetNotificationMetadataQuery> {
    List<NotificationMetadataProjection> execute(GetNotificationMetadataQuery query);
}
