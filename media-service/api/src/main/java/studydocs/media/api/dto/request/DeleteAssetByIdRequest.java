package studydocs.media.api.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record DeleteAssetByIdRequest(UUID id) {
}
