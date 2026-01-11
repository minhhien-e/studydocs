package studydocs.media.application.dto.query;

import lombok.Builder;
import studydocs.media.application.dto.base.Request;
import studydocs.media.application.dto.projection.AssetProjection;

import java.util.UUID;

@Builder
public record GetAssetByIdQuery(UUID id) implements Request<AssetProjection> {
}
