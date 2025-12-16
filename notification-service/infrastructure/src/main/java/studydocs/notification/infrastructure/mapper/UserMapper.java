package studydocs.notification.infrastructure.mapper;

import studydocs.notification.application.dto.projection.UserProjection;
import studydocs.notification.application.dto.view.UserView;

public final class UserMapper {
    private UserMapper() {
    }

    public static UserProjection toProjection(UserView user) {
        return UserProjection.builder()
                .id(user.id())
                .name(user.name())
                .build();
    }
}
