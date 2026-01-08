package studydocs.media.application.dto.query;

import lombok.Builder;
import studydocs.media.application.dto.base.Request;
import studydocs.media.application.dto.projection.FileProjection;

import java.util.UUID;

@Builder
public record GetFileByIdQuery(UUID id) implements Request<FileProjection> {
}
