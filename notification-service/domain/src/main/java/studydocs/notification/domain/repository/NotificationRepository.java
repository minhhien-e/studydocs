package studydocs.notification.domain.repository;

import io.github.domain.repository.AggregateRootWriter;
import studydocs.notification.domain.aggregate.Notification;

import java.util.UUID;

public interface NotificationRepository extends AggregateRootWriter<Notification> {
}
