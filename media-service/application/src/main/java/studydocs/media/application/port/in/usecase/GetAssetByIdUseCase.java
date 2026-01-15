package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.dto.query.GetAssetByIdQuery;
import studydocs.media.application.port.in.usecase.base.UseCase;

public interface GetAssetByIdUseCase extends UseCase<GetAssetByIdQuery, AssetProjection> {
}
