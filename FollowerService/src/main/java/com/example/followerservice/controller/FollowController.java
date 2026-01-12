package com.example.followerservice.controller;

import com.example.followerservice.dto.request.FollowRequest;
import com.example.followerservice.dto.response.FollowResponse;
import com.example.followerservice.service.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * Follow một user
     * Body: { "followerId": 1, "followingId": 2 }
     */
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_FOLLOWS_WRITE'))")
    @ResponseStatus(HttpStatus.CREATED)
    public FollowResponse follow(@Valid @RequestBody FollowRequest request) {
        return followService.follow(request);
    }

    /**
     * Hủy follow hoặc xóa follower
     * @param followerId ID của người đang follow
     * @param followingId ID của người được follow
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('SCOPE_FOLLOWS_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFollow(@RequestParam UUID followerId, @RequestParam UUID followingId) {
        followService.deleteFollow(followerId, followingId);
    }

    /**
     * Lấy danh sách Followers (người theo dõi) của userID
     */
    @GetMapping("/followers/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_FOLLOWS_READ')")
    public List<FollowResponse> getFollowers(@PathVariable UUID userId) {
        return followService.getFollowers(userId);
    }

    /**
     * Lấy danh sách Following (người đang được userID theo dõi)
     */
    @GetMapping("/following/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_FOLLOWS_READ')")
    public List<FollowResponse> getFollowing(@PathVariable UUID userId) {
        return followService.getFollowing(userId);
    }

    /**
     * Đếm số lượng followers
     */
    @GetMapping("/followers/{userId}/count")
    @PreAuthorize("hasAuthority('SCOPE_FOLLOWS_READ')")
    public long countFollowers(@PathVariable UUID userId) {
        return followService.countFollowers(userId);
    }

    /**
     * Đếm số lượng following
     */
    @GetMapping("/following/{userId}/count")
    @PreAuthorize("hasAuthority('SCOPE_FOLLOWS_READ')")
    public long countFollowing(@PathVariable UUID userId) {
        return followService.countFollowing(userId);
    }
}
