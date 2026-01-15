package studydocs.media.application.dto.command;

import lombok.Builder;
import studydocs.media.application.dto.base.Request;

import java.io.InputStream;
import java.util.UUID;

@Builder
public record CreateAssetCommand(
        InputStream fileContent,
        String fileName,
        String contentType,
        long fileSize,
        UUID uploaderId) implements Request<UUID> {
}
