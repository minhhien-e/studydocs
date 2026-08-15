package com.studydocs.modules.system.controller;

import com.studydocs.modules.system.dto.SystemDtos;
import com.studydocs.modules.system.service.MediaService;
import com.studydocs.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/media/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping("/init-upload")
    public ApiResponse<SystemDtos.MediaResponse> initUpload(
            Authentication authentication,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        String userId = (authentication != null) ? authentication.getName() : "anonymous";
        if (file != null) {
            return ApiResponse.success(mediaService.uploadMedia(userId, file));
        }
        return ApiResponse.success(SystemDtos.MediaResponse.builder()
                .mediaId("temp-" + System.currentTimeMillis())
                .status("INITIATED")
                .build());
    }

    @PostMapping("/{mediaId}/complete-upload")
    public ApiResponse<SystemDtos.MediaResponse> completeUpload(
            @PathVariable String mediaId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Authentication authentication) {
        String userId = (authentication != null) ? authentication.getName() : "anonymous";
        if (file != null) {
            return ApiResponse.success(mediaService.uploadMedia(userId, file));
        }
        return ApiResponse.success(mediaService.getMediaById(mediaId));
    }
}
