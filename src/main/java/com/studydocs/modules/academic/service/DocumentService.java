package com.studydocs.modules.academic.service;

import com.studydocs.modules.academic.dto.AcademicDtos;
import com.studydocs.modules.academic.dto.DocumentSummaryDto;
import com.studydocs.modules.academic.entity.DocumentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DocumentService {
    List<DocumentSummaryDto> getMostLiked(int limit);
    List<DocumentSummaryDto> getNewest(int limit);
    List<DocumentSummaryDto> searchDocuments(String keyword);
    DocumentSummaryDto getDocumentById(String id);
    List<DocumentSummaryDto> getMyDocuments(String userId);
    Map<String, Long> getMyDocumentCount(String userId);
    DocumentSummaryDto uploadDocument(String userId, AcademicDtos.DocumentCreateDto dto, MultipartFile file, MultipartFile thumbnail);
    void incrementDownloadCount(String documentId);
    DocumentSummaryDto toDocumentSummaryDto(DocumentEntity entity);
}
