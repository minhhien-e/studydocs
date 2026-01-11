package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.dto.query.GetAssetByIdQuery;
import studydocs.media.application.port.in.usecase.GetAssetByIdUseCasePort;
import studydocs.media.application.port.out.repository.AssetQueries;

@Service
@RequiredArgsConstructor
public class GetAssetByIdUseCase implements GetAssetByIdUseCasePort {
    private final AssetQueries assetQueries;

    @Override
    public AssetProjection execute(GetAssetByIdQuery params) {
        return assetQueries.getById(params.id());
    }
}
