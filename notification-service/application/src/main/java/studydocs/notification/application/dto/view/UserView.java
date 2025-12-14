package studydocs.notification.application.dto.view;

import java.util.UUID;


public record UserView(
        UUID id,
        String name
) {
}
