package studydocs.media.application.port.out.repository;

import studydocs.media.application.dto.projection.AssetProjection;

import java.util.UUID;

public interface AssetQueries {
    AssetProjection getById(UUID id);
}
