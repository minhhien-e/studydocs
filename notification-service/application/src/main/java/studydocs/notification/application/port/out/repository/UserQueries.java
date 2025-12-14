package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.readmodel.UserReadModel;

import java.util.UUID;

public interface UserQueries {
     UserReadModel getById(UUID id);
}
