package com.studydocs.modules.system.service;

import com.studydocs.modules.system.dto.SystemDtos;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    SystemDtos.MediaResponse uploadMedia(String userId, MultipartFile file);
    SystemDtos.MediaResponse getMediaById(String mediaId);
}
