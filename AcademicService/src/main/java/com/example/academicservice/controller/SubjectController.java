package com.example.academicservice.controller;

import com.example.academicservice.dto.request.SubjectCreateRequest;
import com.example.academicservice.dto.request.SubjectUpdateRequest;
import com.example.academicservice.dto.response.SubjectResponse;
import com.example.academicservice.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

/**
 * Controller để xử lý các HTTP requests liên quan đến Subject
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * Lấy thông tin môn học theo ID
     */
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public SubjectResponse getSubjectById(@PathVariable UUID id) {
        return subjectService.getSubjectById(id);
    }

    /**
     * Tạo mới môn học
     */
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public SubjectResponse createSubject(@Valid @RequestBody SubjectCreateRequest request) {
        return subjectService.createSubject(request);
    }

    /**
     * Cập nhật thông tin môn học theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @PutMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public SubjectResponse updateSubjectById(@PathVariable UUID id,
                                        @RequestParam UUID universityId,
                                        @Valid @RequestBody SubjectUpdateRequest request) {
        return subjectService.updateSubject(id, universityId, request);
    }

    /**
     * Cập nhật thông tin môn học theo slug
     * Bắt buộc phải có universityId và departmentId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @PutMapping("/slug/{slug}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public SubjectResponse updateSubjectBySlug(@PathVariable String slug,
                                          @RequestParam UUID universityId,
                                          @RequestParam UUID departmentId,
                                          @Valid @RequestBody SubjectUpdateRequest request) {
        return subjectService.updateSubjectBySlug(universityId, departmentId, slug, request);
    }

    /**
     * Xóa môn học theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public void deleteSubjectById(@PathVariable UUID id, @RequestParam UUID universityId) {
        subjectService.deleteSubjectById(id, universityId);
    }

    /**
     * Xóa môn học theo slug
     * Bắt buộc phải có universityId và departmentId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @DeleteMapping("/slug/{slug}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public void deleteSubjectBySlug(@PathVariable String slug,
                                 @RequestParam UUID universityId,
                                 @RequestParam UUID departmentId) {
        subjectService.deleteSubjectBySlug(universityId, departmentId, slug);
    }

    /**
     * Filter subjects với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param facultyId - ID khoa (optional)
     * @param facultySlug - Slug khoa (optional)
     * @param departmentId - ID bộ môn (optional)
     * @param departmentSlug - Slug bộ môn (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách môn học
     */
    @GetMapping("/filter")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public List<SubjectResponse> filterSubjects(
            @RequestParam(required = false) UUID universityId,
            @RequestParam(required = false) String universitySlug,
            @RequestParam(required = false) UUID facultyId,
            @RequestParam(required = false) String facultySlug,
            @RequestParam(required = false) UUID departmentId,
            @RequestParam(required = false) String departmentSlug,
            @RequestParam(required = false) Boolean isActive) {
        return subjectService.filter(universityId, universitySlug, facultyId, 
                                  facultySlug, departmentId, departmentSlug, isActive);
    }
}
