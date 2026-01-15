package studydocs.media.infrastructure.adapter.bus.handler;

import org.springframework.stereotype.Component;
import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.dto.query.GetAssetByIdQuery;
import studydocs.media.application.port.in.usecase.GetAssetByIdUseCasePort;
import studydocs.media.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class GetAssetByIdHandler extends AbstractHandler<GetAssetByIdQuery, AssetProjection> {
    protected GetAssetByIdHandler(GetAssetByIdUseCasePort useCase) {
        super(useCase, GetAssetByIdQuery.class);
    }
}
