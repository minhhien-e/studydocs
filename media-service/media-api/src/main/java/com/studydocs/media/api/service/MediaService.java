package com.studydocs.media.api.service;

import com.studydocs.media.api.dto.request.InitUploadRequest;
import com.studydocs.media.api.dto.response.InitUploadResponse;
import com.studydocs.media.api.dto.response.MediaStatusResponse;

import java.util.UUID;

public interface MediaService {
    InitUploadResponse initUpload(InitUploadRequest request);
    void completeUpload(UUID mediaId);
    MediaStatusResponse getMediaById(UUID mediaId);
}
