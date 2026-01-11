package studydocs.media.api.mapper;

import org.springframework.web.multipart.MultipartFile;
import studydocs.media.api.dto.request.DeleteAssetByIdRequest;
import studydocs.media.api.dto.request.GetAssetByIdRequest;
import studydocs.media.api.dto.request.UploadAssetRequest;
import studydocs.media.api.dto.view.AssetView;
import studydocs.media.api.dto.view.PreviewDataView;
import studydocs.media.application.dto.command.DeleteAssetByIdCommand;
import studydocs.media.application.dto.command.UploadAssetCommand;
import studydocs.media.application.dto.projection.AssetProjection;
import studydocs.media.application.dto.query.GetAssetByIdQuery;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class AssetMapper {
    /// Command
    public static UploadAssetCommand toCommand(UUID userId, UploadAssetRequest request) {
        MultipartFile file = request.file();
        try {
            return UploadAssetCommand.builder()
                    .fileContent(file.getInputStream())
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .fileSize(file.getSize())
                    .uploaderId(userId)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to get input stream", e);
        }
    }

    public static DeleteAssetByIdCommand toCommand(UUID userId, DeleteAssetByIdRequest request) {
        return DeleteAssetByIdCommand.builder()
                .assetId(request.id())
                .userId(userId)
                .build();

    }

    /// Query
    public static GetAssetByIdQuery toQuery(GetAssetByIdRequest request) {
        return GetAssetByIdQuery.builder()
                .id(request.id())
                .build();
    }

    /// View
    public static AssetView toView(AssetProjection projection) {
        return AssetView.builder()
                .id(projection.id())
                .assetName(projection.assetName())
                .size(projection.size())
                .contentType(projection.contentType())
                .totalPages(projection.totalPages())
                .previewDataView(projection.previewData() != null ? PreviewDataView.builder()
                        .baseUrl(projection.previewData().baseUrl())
                        .key(projection.previewData().key())
                        .build() : null)
                .downloadUrl(projection.downloadUrl() != null ? projection.downloadUrl() : null)
                .status(projection.status())
                .uploadProgress(projection.uploadProgress())
                .build();
    }

}
