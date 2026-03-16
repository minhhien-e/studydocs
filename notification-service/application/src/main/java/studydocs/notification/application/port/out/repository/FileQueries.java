package studydocs.notification.application.port.out.repository;

import studydocs.notification.application.dto.projection.FileProjection;

import java.util.UUID;

public interface FileQueries {
    FileProjection getById(UUID id);
}
