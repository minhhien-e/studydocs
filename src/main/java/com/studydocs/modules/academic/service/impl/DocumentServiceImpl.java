package com.studydocs.modules.academic.service.impl;

import com.studydocs.infras.storage.FileStorageService;
import com.studydocs.modules.academic.dto.AcademicDtos;
import com.studydocs.modules.academic.dto.DocumentSummaryDto;
import com.studydocs.modules.academic.entity.DocumentEntity;
import com.studydocs.modules.academic.repository.DocumentRepository;
import com.studydocs.modules.academic.service.DocumentService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final FileStorageService fileStorageService;

    @Override
    @Transactional(readOnly = true)
    public List<DocumentSummaryDto> getMostLiked(int limit) {
        return documentRepository.findAllByOrderByLikeCountDesc(PageRequest.of(0, limit))
                .stream()
                .map(this::toDocumentSummaryDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentSummaryDto> getNewest(int limit) {
        return documentRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(0, limit))
                .stream()
                .map(this::toDocumentSummaryDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentSummaryDto> searchDocuments(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getNewest(20);
        }
        return documentRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(this::toDocumentSummaryDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentSummaryDto getDocumentById(String id) {
        DocumentEntity entity = documentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DOCUMENT_NOT_FOUND));
        return toDocumentSummaryDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentSummaryDto> getMyDocuments(String userId) {
        return documentRepository.findByUserId(userId).stream()
                .map(this::toDocumentSummaryDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Long> getMyDocumentCount(String userId) {
        long count = documentRepository.countByUserId(userId);
        return Map.of("count", count);
    }

    @Override
    @Transactional
    public DocumentSummaryDto uploadDocument(String userId, AcademicDtos.DocumentCreateDto dto, MultipartFile file, MultipartFile thumbnail) {
        String fileUrl = fileStorageService.storeFile(file, "documents");
        String thumbnailUrl = (thumbnail != null) ? fileStorageService.storeFile(thumbnail, "thumbnails") : null;

        DocumentEntity entity = DocumentEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory() != null ? dto.getCategory() : "General")
                .school(dto.getSchool() != null ? dto.getSchool() : "General")
                .pageCount(dto.getPageCount() != null ? dto.getPageCount() : 1)
                .academicYear(dto.getYear() != null ? dto.getYear() : "2024")
                .fileUrl(fileUrl)
                .thumbnailUrl(thumbnailUrl)
                .userId(userId)
                .subjectId(dto.getSubjectId())
                .universityId(dto.getUniversityId())
                .likeCount(0)
                .commentCount(0)
                .downloadCount(0)
                .build();

        DocumentEntity saved = documentRepository.save(entity);
        return toDocumentSummaryDto(saved);
    }

    @Override
    @Transactional
    public void incrementDownloadCount(String documentId) {
        DocumentEntity entity = documentRepository.findById(documentId)
                .orElseThrow(() -> new AppException(ErrorCode.DOCUMENT_NOT_FOUND));
        entity.setDownloadCount(entity.getDownloadCount() + 1);
        documentRepository.save(entity);
    }

    @Override
    public DocumentSummaryDto toDocumentSummaryDto(DocumentEntity entity) {
        return DocumentSummaryDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .thumbnail(entity.getThumbnailUrl())
                .category(entity.getCategory() != null ? entity.getCategory() : "General")
                .school(entity.getSchool() != null ? entity.getSchool() : "General")
                .pageCount(entity.getPageCount() != null ? entity.getPageCount() : 1)
                .year(entity.getAcademicYear() != null ? entity.getAcademicYear() : "2024")
                .likeCount(entity.getLikeCount() != null ? entity.getLikeCount() : 0)
                .commentCount(entity.getCommentCount() != null ? entity.getCommentCount() : 0)
                .isLiked(false)
                .isBookmarked(false)
                .build();
    }
}
