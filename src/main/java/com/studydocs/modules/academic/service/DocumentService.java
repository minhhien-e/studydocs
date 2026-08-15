package com.studydocs.modules.academic.service;

import com.studydocs.modules.academic.dto.AcademicDtos;
import com.studydocs.modules.academic.dto.DocumentSummaryDto;
import com.studydocs.modules.academic.entity.DocumentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Dịch vụ quản lý catalogue tài liệu học tập, tìm kiếm, tải file và thống kê.
 *
 * @author StudyDocs Team
 * @since 1.0.0
 */
public interface DocumentService {

    /**
     * Lấy danh sách tài liệu được yêu thích (Like) nhiều nhất.
     *
     * @param limit Số lượng tối đa trả về
     * @return Danh sách {@link DocumentSummaryDto}
     */
    List<DocumentSummaryDto> getMostLiked(int limit);

    /**
     * Lấy danh sách tài liệu mới đăng gần đây.
     *
     * @param limit Số lượng tối đa trả về
     * @return Danh sách {@link DocumentSummaryDto}
     */
    List<DocumentSummaryDto> getNewest(int limit);

    /**
     * Tìm kiếm tài liệu theo từ khóa (tiêu đề, mô tả).
     *
     * @param keyword Từ khóa tìm kiếm
     * @return Danh sách {@link DocumentSummaryDto} khớp với từ khóa
     */
    List<DocumentSummaryDto> searchDocuments(String keyword);

    /**
     * Lấy thông tin chi tiết một tài liệu theo ID.
     *
     * @param id ID tài liệu
     * @return {@link DocumentSummaryDto}
     */
    DocumentSummaryDto getDocumentById(String id);

    /**
     * Lấy danh sách tài liệu do một người dùng cụ thể đã tải lên.
     *
     * @param userId ID người dùng
     * @return Danh sách tài liệu của người dùng
     */
    List<DocumentSummaryDto> getMyDocuments(String userId);

    /**
     * Lấy tổng số lượng tài liệu do người dùng đã đăng.
     *
     * @param userId ID người dùng
     * @return Map chứa key "count" và tổng số lượng
     */
    Map<String, Long> getMyDocumentCount(String userId);

    /**
     * Tải lên tài liệu học tập mới.
     *
     * @param userId ID người dùng đăng bài
     * @param dto Thông tin tiêu đề, mô tả, môn học, trường học
     * @param file File tài liệu chính (PDF, DOCX...)
     * @param thumbnail File ảnh xem trước (Thumbnail)
     * @return {@link DocumentSummaryDto} của tài liệu vừa đăng thành công
     */
    DocumentSummaryDto uploadDocument(String userId, AcademicDtos.DocumentCreateDto dto, MultipartFile file, MultipartFile thumbnail);

    /**
     * Tăng số lượt tải của tài liệu khi người dùng download.
     *
     * @param documentId ID tài liệu
     */
    void incrementDownloadCount(String documentId);

    /**
     * Chuyển đổi từ JPA Entity {@link DocumentEntity} sang DTO {@link DocumentSummaryDto}.
     *
     * @param entity Entity tài liệu
     * @return DTO tài liệu rút gọn
     */
    DocumentSummaryDto toDocumentSummaryDto(DocumentEntity entity);
}
