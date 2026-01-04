package com.example.academicservice.controller;

import com.example.academicservice.dto.request.UniversityCreateRequest;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller để xử lý các HTTP requests liên quan đến University
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/universities")
public class UniversityController {

    private final UniversityService universityService;

    /**
     * Lấy trường đại học theo ID
     */
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('READ_USER')")
    public UniversityResponse getUniversityById(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    /**
     * Tạo mới trường đại học
     */
    @PostMapping
    public UniversityResponse createUniversity(@RequestBody UniversityCreateRequest request) {
        return universityService.createUniversity(request);
    }

    /**
     * Cập nhật thông tin trường đại học theo ID
     */
    @PutMapping("/id/{id}")
    public UniversityResponse updateUniversityById(@PathVariable Long id,
                                                   @RequestBody UniversityCreateRequest request) {
        return universityService.updateUniversity(id, request);
    }

    /**
     * Cập nhật thông tin trường đại học theo slug
     */
    @PutMapping("/slug/{slug}")
    public UniversityResponse updateUniversityBySlug(@PathVariable String slug,
                                                     @RequestBody UniversityCreateRequest request) {
        return universityService.updateUniversityBySlug(slug, request);
    }

    /**
     * Xóa trường đại học theo ID
     */
    @DeleteMapping("/id/{id}")
    public void deleteUniversityById(@PathVariable Long id) {
        universityService.deleteUniversityById(id);
    }

    /**
     * Xóa trường đại học theo slug
     */
    @DeleteMapping("/slug/{slug}")
    public void deleteUniversityBySlug(@PathVariable String slug) {
        universityService.deleteUniversityBySlug(slug);
    }

    /**
     * Filter universities với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param id - ID trường đại học (optional)
     * @param slug - Slug trường đại học (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách trường đại học
     */
    @GetMapping("/filter")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public List<UniversityResponse> filterUniversities(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String slug,
            @RequestParam(required = false) Boolean isActive) {
        return universityService.filter(id, slug, isActive);
    }
}
