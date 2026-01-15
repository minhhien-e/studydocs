package studydocs.media.domain.vo;

import java.util.UUID;

public record OutboxMessage(
        UUID id,
        String type,
        String payload) {
}
