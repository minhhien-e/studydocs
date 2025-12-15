package studydocs.notification.application.port.out.repository;

import io.github.domain.entity.Outbox;

public interface LockingOutboxRepository {
    Outbox findAndLockNextEvent();
}
