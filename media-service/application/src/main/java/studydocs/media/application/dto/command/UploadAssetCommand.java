package studydocs.media.application.dto.command;

import lombok.Builder;
import studydocs.media.application.dto.base.Request;

import java.io.InputStream;
import java.util.UUID;

import studydocs.media.application.dto.projection.AssetProjection;

@Builder
public record UploadAssetCommand(
                InputStream fileContent,
                String fileName,
                String contentType,
                long fileSize,
                UUID uploaderId) implements Request<AssetProjection> {
}
