package com.example.followerservice.service;

import com.example.followerservice.dto.request.FollowRequest;
import com.example.followerservice.dto.response.FollowResponse;
import com.example.followerservice.entity.Follow;
import com.example.followerservice.exception.ApiException;
import com.example.followerservice.exception.FollowErrorCodes;
import com.example.followerservice.mapper.FollowMapper;
import com.example.followerservice.remote.follow.PublishNotificationFollowed;
import com.example.followerservice.remote.follow.dto.UserFollowedPayload;
import com.example.followerservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final FollowMapper followMapper;
    private final PublishNotificationFollowed publishNotificationFollowed;

    /**
     * Thực hiện follow user khác
     */
    public FollowResponse follow(FollowRequest request) {
        log.info("Request to follow: followerId={} -> followingId={}", request.getFollowerId(), request.getFollowingId());

        // Check self-follow
        if (request.getFollowerId().equals(request.getFollowingId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, FollowErrorCodes.CANNOT_FOLLOW_SELF,
                    "Cannot follow yourself");
        }

        // Check duplicate
        if (followRepository.existsByFollowerIdAndFollowingId(request.getFollowerId(), request.getFollowingId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, FollowErrorCodes.ALREADY_FOLLOWING,
                    "Already following this user");
        }

        Follow follow = followMapper.toEntity(request);
        Follow savedFollow = followRepository.save(follow);

        // Publish event to notify the followed user
        publishNotificationFollowed.publishUserFollowed( new UserFollowedPayload(
                request.getFollowerId(),
                request.getFollowingId()
        ));

        return followMapper.toResponse(savedFollow);
    }

    /**
     * Hủy follow (Unfollow) hoặc Xóa follower
     * Cả 2 hành động đều là xóa record trong bảng follows
     */
    public void deleteFollow(UUID followerId, UUID followingId) {
        log.info("Request to delete follow: followerId={} -> followingId={}", followerId, followingId);

        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, FollowErrorCodes.FOLLOW_NOT_FOUND,
                        "Follow relationship not found"));

        followRepository.delete(follow);
    }

    /**
     * Lấy danh sách những người đang follow User (Followers)
     */
    @Transactional(readOnly = true)
    public List<FollowResponse> getFollowers(UUID userId) {
        log.info("Fetching followers for userId: {}", userId);
        return followRepository.findByFollowingId(userId).stream()
                .map(followMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy danh sách những người mà User đang follow (Following)
     */
    @Transactional(readOnly = true)
    public List<FollowResponse> getFollowing(UUID userId) {
        log.info("Fetching following list for userId: {}", userId);
        return followRepository.findByFollowerId(userId).stream()
                .map(followMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Đếm số lượng follower
     */
    @Transactional(readOnly = true)
    public long countFollowers(UUID userId) {
        return followRepository.countByFollowingId(userId);
    }

    /**
     * Đếm số lượng following
     */
    @Transactional(readOnly = true)
    public long countFollowing(UUID userId) {
        return followRepository.countByFollowerId(userId);
    }

    /*
     * kiểm tra có đang follow hay không
     * */

    @Transactional(readOnly = true)
    public boolean isFollowing(UUID followerId, UUID followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
}
