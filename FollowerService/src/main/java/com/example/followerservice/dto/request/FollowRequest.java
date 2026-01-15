package com.example.followerservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class FollowRequest {
    @NotNull(message = "Follower ID cannot be null")
    private UUID followerId;

    @NotNull(message = "Following ID cannot be null")
    private UUID followingId;
}
