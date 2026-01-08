package studydocs.media.application.dto.command;

import lombok.Builder;
import studydocs.media.application.dto.base.Request;

import java.util.UUID;
@Builder
public record DeleteFileByIdCommand(
    UUID fileId,
    UUID userId
) implements Request<Void> {
}

