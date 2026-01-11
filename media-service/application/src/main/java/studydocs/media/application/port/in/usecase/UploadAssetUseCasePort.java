package studydocs.media.application.port.in.usecase;

import studydocs.media.application.dto.command.UploadAssetCommand;
import studydocs.media.application.port.in.usecase.base.UseCase;

import studydocs.media.application.dto.projection.AssetProjection;

public interface UploadAssetUseCasePort extends UseCase<UploadAssetCommand, AssetProjection> {
}
