package com.example.followerservice.dto.response;

import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class FollowResponse {
    private UUID id;
    private UUID followerId;
    private UUID followingId;
    private Timestamp createdAt;
}
