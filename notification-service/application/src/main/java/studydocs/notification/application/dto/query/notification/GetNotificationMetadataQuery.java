package studydocs.notification.application.dto.query.notification;

import studydocs.notification.application.dto.base.Request;
import studydocs.notification.application.dto.projection.NotificationMetadataProjection;

import java.util.List;

public record GetNotificationMetadataQuery() implements Request<List<NotificationMetadataProjection>> {
}
