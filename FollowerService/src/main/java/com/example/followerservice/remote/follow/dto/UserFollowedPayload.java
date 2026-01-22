package com.example.followerservice.remote.follow.dto;

import java.util.UUID;

public record UserFollowedPayload(UUID followerId, UUID followedId) {
}
