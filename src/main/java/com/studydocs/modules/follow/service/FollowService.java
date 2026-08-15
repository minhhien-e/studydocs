package com.studydocs.modules.follow.service;

import com.studydocs.modules.user.dto.UserDto;

import java.util.List;

public interface FollowService {
    void followUser(String followerId, String targetUserId);
    void unfollowUser(String followerId, String targetUserId);
    List<UserDto> getFollowers(String userId);
    List<UserDto> getFollowing(String userId);
}
