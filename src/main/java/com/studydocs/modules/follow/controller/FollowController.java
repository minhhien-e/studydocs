package com.studydocs.modules.follow.controller;

import com.studydocs.modules.follow.service.FollowService;
import com.studydocs.modules.user.dto.UserDto;
import com.studydocs.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/followers")
    public ApiResponse<List<UserDto>> getMyFollowers(Authentication authentication) {
        String userId = authentication.getName();
        return ApiResponse.success(followService.getFollowers(userId));
    }

    @GetMapping("/following")
    public ApiResponse<List<UserDto>> getMyFollowing(Authentication authentication) {
        String userId = authentication.getName();
        return ApiResponse.success(followService.getFollowing(userId));
    }

    @PostMapping("/{targetUserId}/follow")
    public ApiResponse<String> followUser(Authentication authentication, @PathVariable String targetUserId) {
        String currentUserId = authentication.getName();
        followService.followUser(currentUserId, targetUserId);
        return ApiResponse.success("User followed successfully");
    }

    @PostMapping("/{targetUserId}/unfollow")
    public ApiResponse<String> unfollowUser(Authentication authentication, @PathVariable String targetUserId) {
        String currentUserId = authentication.getName();
        followService.unfollowUser(currentUserId, targetUserId);
        return ApiResponse.success("User unfollowed successfully");
    }
}
