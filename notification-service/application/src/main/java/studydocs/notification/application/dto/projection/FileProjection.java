package studydocs.notification.application.dto.projection;

import lombok.Builder;

import java.util.UUID;

@Builder
public record FileProjection(UUID id,
                             String name,
                             long size,
                             int totalPage) {

}
