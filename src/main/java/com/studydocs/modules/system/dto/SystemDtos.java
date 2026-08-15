package com.studydocs.modules.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class SystemDtos {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MediaResponse {
        private String mediaId;
        private String fileName;
        private String fileUrl;
        private String contentType;
        private Long sizeBytes;
        private String status;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationDto {
        private String id;
        private String userId;
        private String title;
        private String content;
        private String type;
        private Boolean isRead;
        private Boolean isDeleted;
        private LocalDateTime receivedAt;
    }
}
