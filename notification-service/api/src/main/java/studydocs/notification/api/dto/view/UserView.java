package studydocs.notification.api.dto.view;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserView(
        UUID id,
        String name
) {
}
