package studydocs.media.application.service.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studydocs.media.application.dto.command.CreateAssetCommand;
import studydocs.media.application.dto.payload.AssetUploadRequestedPayload;
import studydocs.media.application.port.in.usecase.CreateAssetUseCase;
import studydocs.media.application.port.out.messaging.PublishAssetEventPort;
import studydocs.media.application.port.out.storage.AssetTempStoragePort;
import studydocs.media.application.port.out.validation.AssetContentValidationPort;
import studydocs.media.domain.aggregate.Asset;
import studydocs.media.domain.repository.AssetWriter;
import studydocs.media.domain.vo.AssetContentType;
import studydocs.media.domain.vo.AssetName;
import studydocs.media.domain.vo.AssetSize;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateAssetService implements CreateAssetUseCase {
    private final AssetWriter assetWriter;
    private final PublishAssetEventPort publishAssetEventPort;
    private final AssetContentValidationPort assetContentValidationPort;
    private final AssetTempStoragePort assetTempStoragePort;

    @Override
    public UUID execute(CreateAssetCommand params) {
        Path tempFile = null;
        InputStream validatedStream = assetContentValidationPort.validate(params.fileContent(), params.fileName());

        tempFile = assetTempStoragePort.store(validatedStream, params.fileName());

        var assetId = UUID.randomUUID();

        var asset = Asset.create(
                assetId,
                params.uploaderId(),
                AssetName.of(params.fileName()),
                AssetSize.of(params.fileSize()),
                AssetContentType.of(params.contentType()),
                null,
                null);

        asset = assetWriter.saveAndReturn(asset);

        publishAssetEventPort.publish(new AssetUploadRequestedPayload(
                assetId,
                tempFile.toAbsolutePath().toString(),
                params.fileName(),
                params.contentType(),
                params.fileSize()));

        tempFile = null;

        return asset.getId();
    }
}
