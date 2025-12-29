package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.UserProjection;

import java.util.UUID;

public interface UserQueries {
     UserProjection getById(UUID id);
}
