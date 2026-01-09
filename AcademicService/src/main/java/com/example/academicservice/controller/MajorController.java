package com.example.academicservice.controller;

import com.example.academicservice.dto.request.MajorCreateRequest;
import com.example.academicservice.dto.request.MajorUpdateRequest;
import com.example.academicservice.dto.response.MajorResponse;
import com.example.academicservice.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller để xử lý các HTTP requests liên quan đến Major
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/majors")
public class MajorController {

    private final MajorService majorService;

    /**
     * Lấy thông tin ngành theo ID
     */
    @GetMapping("/id/{id}")
    public MajorResponse getMajorById(@PathVariable UUID id) {
        return majorService.getMajorById(id);
    }

    /**
     * Tạo mới ngành
     */
    @PostMapping
    public MajorResponse createMajor(@RequestBody MajorCreateRequest request) {
        return majorService.createMajor(request);
    }

    /**
     * Cập nhật thông tin ngành theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @PutMapping("/id/{id}")
    public MajorResponse updateMajorById(@PathVariable UUID id,
                                        @RequestParam UUID universityId,
                                        @RequestBody MajorUpdateRequest request) {
        return majorService.updateMajor(id, universityId, request);
    }

    /**
     * Cập nhật thông tin ngành theo slug
     * Bắt buộc phải có universityId và departmentId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @PutMapping("/slug/{slug}")
    public MajorResponse updateMajorBySlug(@PathVariable String slug,
                                          @RequestParam UUID universityId,
                                          @RequestParam UUID departmentId,
                                          @RequestBody MajorUpdateRequest request) {
        return majorService.updateMajorBySlug(universityId, departmentId, slug, request);
    }

    /**
     * Xóa ngành theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @DeleteMapping("/id/{id}")
    public void deleteMajorById(@PathVariable UUID id, @RequestParam UUID universityId) {
        majorService.deleteMajorById(id, universityId);
    }

    /**
     * Xóa ngành theo slug
     * Bắt buộc phải có universityId và departmentId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @DeleteMapping("/slug/{slug}")
    public void deleteMajorBySlug(@PathVariable String slug,
                                 @RequestParam UUID universityId,
                                 @RequestParam UUID departmentId) {
        majorService.deleteMajorBySlug(universityId, departmentId, slug);
    }

    /**
     * Filter majors với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param facultyId - ID khoa (optional)
     * @param facultySlug - Slug khoa (optional)
     * @param departmentId - ID bộ môn (optional)
     * @param departmentSlug - Slug bộ môn (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách ngành học
     */
    @GetMapping("/filter")
    public List<MajorResponse> filterMajors(
            @RequestParam(required = false) UUID universityId,
            @RequestParam(required = false) String universitySlug,
            @RequestParam(required = false) UUID facultyId,
            @RequestParam(required = false) String facultySlug,
            @RequestParam(required = false) UUID departmentId,
            @RequestParam(required = false) String departmentSlug,
            @RequestParam(required = false) Boolean isActive) {
        return majorService.filter(universityId, universitySlug, facultyId, 
                                  facultySlug, departmentId, departmentSlug, isActive);
    }
}

