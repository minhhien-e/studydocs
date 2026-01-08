package studydocs.media.application.port.out.repository;

import studydocs.media.application.dto.projection.FileProjection;

import java.util.UUID;

public interface FileQueries {
    FileProjection getById(UUID id);
}

