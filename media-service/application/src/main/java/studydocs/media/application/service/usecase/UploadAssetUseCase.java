package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.CreateAssetCommand;
import studydocs.media.application.dto.command.UploadAssetCommand;
import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.dto.query.GetAssetByIdQuery;
import studydocs.media.application.port.in.usecase.CreateAssetUseCasePort;
import studydocs.media.application.port.in.usecase.GetAssetByIdUseCasePort;
import studydocs.media.application.port.in.usecase.UploadAssetUseCasePort;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadAssetUseCase implements UploadAssetUseCasePort {

    private final CreateAssetUseCasePort createAssetUseCasePort;
    private final GetAssetByIdUseCasePort getAssetByIdUseCasePort;

    @Override
    public AssetProjection execute(UploadAssetCommand params) {
        var createParam = CreateAssetCommand.builder()
                .fileContent(params.fileContent())
                .fileName(params.fileName())
                .contentType(params.contentType())
                .fileSize(params.fileSize())
                .uploaderId(params.uploaderId())
                .build();
        UUID assetId = createAssetUseCasePort.execute(createParam);
        return getAssetByIdUseCasePort.execute(new GetAssetByIdQuery(assetId));
    }
}
