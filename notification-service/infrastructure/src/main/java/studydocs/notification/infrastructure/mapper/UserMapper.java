package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.UserProjection;
import studydocs.notification.infrastructure.dto.integration.UserIntegration;

public final class UserMapper {
    private UserMapper() {
    }

    public static UserProjection toProjection(UserIntegration user) {
        return UserProjection.builder()
                .id(user.id())
                .name(user.name())
                .build();
    }
}
