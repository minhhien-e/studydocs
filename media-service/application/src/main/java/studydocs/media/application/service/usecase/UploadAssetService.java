package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.CreateAssetCommand;
import studydocs.media.application.dto.command.UploadAssetCommand;
import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.dto.query.GetAssetByIdQuery;
import studydocs.media.application.port.in.usecase.CreateAssetUseCase;
import studydocs.media.application.port.in.usecase.GetAssetByIdUseCase;
import studydocs.media.application.port.in.usecase.UploadAssetUseCase;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadAssetService implements UploadAssetUseCase {

    private final CreateAssetUseCase createAssetUseCase;
    private final GetAssetByIdUseCase getAssetByIdUseCase;

    @Override
    public AssetProjection execute(UploadAssetCommand params) {
        log.debug("Received upload request for file: {}, size: {}, uploader: {}",
                params.fileName(), params.fileSize(), params.uploaderId());

        var createParam = CreateAssetCommand.builder()
                .fileContent(params.fileContent())
                .fileName(params.fileName())
                .contentType(params.contentType())
                .fileSize(params.fileSize())
                .uploaderId(params.uploaderId())
                .build();
        UUID assetId = createAssetUseCase.execute(createParam);

        log.debug("Asset created successfully with ID: {}. Fetching projection...", assetId);

        AssetProjection projection = getAssetByIdUseCase.execute(new GetAssetByIdQuery(assetId));

        log.debug("Returning projection for asset: {}", assetId);
        return projection;
    }
}
