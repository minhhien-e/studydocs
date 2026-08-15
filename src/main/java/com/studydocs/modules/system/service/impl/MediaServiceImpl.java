package com.studydocs.modules.system.service.impl;

import com.studydocs.infras.storage.FileStorageService;
import com.studydocs.modules.system.dto.SystemDtos;
import com.studydocs.modules.system.entity.MediaAssetEntity;
import com.studydocs.modules.system.repository.MediaAssetRepository;
import com.studydocs.modules.system.service.MediaService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaAssetRepository mediaAssetRepository;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional
    public SystemDtos.MediaResponse uploadMedia(String userId, MultipartFile file) {
        String fileUrl = fileStorageService.storeFile(file, "media");

        MediaAssetEntity entity = MediaAssetEntity.builder()
                .fileName(file.getOriginalFilename())
                .fileUrl(fileUrl)
                .contentType(file.getContentType())
                .sizeBytes(file.getSize())
                .userId(userId)
                .status("COMPLETED")
                .build();

        MediaAssetEntity saved = mediaAssetRepository.save(entity);

        return SystemDtos.MediaResponse.builder()
                .mediaId(saved.getId())
                .fileName(saved.getFileName())
                .fileUrl(saved.getFileUrl())
                .contentType(saved.getContentType())
                .sizeBytes(saved.getSizeBytes())
                .status(saved.getStatus())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public SystemDtos.MediaResponse getMediaById(String mediaId) {
        MediaAssetEntity entity = mediaAssetRepository.findById(mediaId)
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND, "Media asset not found"));

        return SystemDtos.MediaResponse.builder()
                .mediaId(entity.getId())
                .fileName(entity.getFileName())
                .fileUrl(entity.getFileUrl())
                .contentType(entity.getContentType())
                .sizeBytes(entity.getSizeBytes())
                .status(entity.getStatus())
                .build();
    }
}
