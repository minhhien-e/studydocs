package studydocs.notification.infrastructure.adapter.repository.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studydocs.notification.application.dto.projection.NotificationProjection;
import studydocs.notification.application.port.out.repository.NotificationQueries;
import studydocs.notification.domain.exception.notification.NotificationNotFoundException;
import studydocs.notification.infrastructure.mapper.NotificationMapper;
import studydocs.notification.infrastructure.persistence.repository.NotificationMongoRepository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class NotificationQueryAdapter implements NotificationQueries {
    private final NotificationMongoRepository notificationMongoRepository;

    @Override
    public NotificationProjection getById(UUID id) {
        return notificationMongoRepository.findById(id).map(
                NotificationMapper::toProjection
        ).orElseThrow(() -> new NotificationNotFoundException(id));
    }
}
