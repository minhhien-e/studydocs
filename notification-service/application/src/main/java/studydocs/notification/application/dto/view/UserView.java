package studydocs.notification.application.dto.view;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserView(
        UUID id,
        String name
) {
}
