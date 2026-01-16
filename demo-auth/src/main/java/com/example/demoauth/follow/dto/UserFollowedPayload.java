package studydocs.notification.publisher.follow.dto;

import java.util.UUID;

public record UserFollowedPayload(UUID followerId, UUID followedId) {
}
