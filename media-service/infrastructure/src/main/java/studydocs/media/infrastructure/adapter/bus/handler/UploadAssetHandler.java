package studydocs.media.infrastructure.adapter.bus.handler;

import org.springframework.stereotype.Component;
import studydocs.media.application.dto.command.UploadAssetCommand;
import studydocs.media.application.port.in.usecase.UploadAssetUseCasePort;
import studydocs.media.infrastructure.adapter.bus.handler.base.AbstractHandler;

import studydocs.media.application.dto.projection.AssetProjection;

@Component
public class UploadAssetHandler extends AbstractHandler<UploadAssetCommand, AssetProjection> {
    protected UploadAssetHandler(UploadAssetUseCasePort useCase) {
        super(useCase, UploadAssetCommand.class);
    }
}
