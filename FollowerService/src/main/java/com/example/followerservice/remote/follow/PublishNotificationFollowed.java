package com.example.followerservice.remote.follow;

import com.example.followerservice.remote.follow.dto.UserFollowedPayload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;


public interface PublishNotificationFollowed {

     void publishUserFollowed(UserFollowedPayload payload);
}
