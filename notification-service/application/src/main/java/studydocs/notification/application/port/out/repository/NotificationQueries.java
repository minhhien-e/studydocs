package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.NotificationProjection;

import java.util.UUID;

public interface NotificationQueries {
    NotificationProjection getById(UUID id);
}
