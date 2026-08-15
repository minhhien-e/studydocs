package com.studydocs.modules.follow.service.impl;

import com.studydocs.modules.follow.entity.UserFollowEntity;
import com.studydocs.modules.follow.repository.FollowRepository;
import com.studydocs.modules.follow.service.FollowService;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.modules.user.service.UserService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void followUser(String followerId, String targetUserId) {
        if (followerId.equals(targetUserId)) {
            throw new AppException(ErrorCode.CANNOT_FOLLOW_SELF);
        }

        if (!followRepository.existsByFollowerIdAndFollowingId(followerId, targetUserId)) {
            UserFollowEntity follow = UserFollowEntity.builder()
                    .followerId(followerId)
                    .followingId(targetUserId)
                    .build();
            followRepository.save(follow);
        }
    }

    @Override
    @Transactional
    public void unfollowUser(String followerId, String targetUserId) {
        followRepository.deleteByFollowerIdAndFollowingId(followerId, targetUserId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getFollowers(String userId) {
        return followRepository.findByFollowingId(userId).stream()
                .map(follow -> userService.getUserById(follow.getFollowerId()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getFollowing(String userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(follow -> userService.getUserById(follow.getFollowingId()))
                .toList();
    }
}
