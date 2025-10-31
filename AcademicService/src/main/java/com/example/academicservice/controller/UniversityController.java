package com.example.academicservice.controller;

import com.example.academicservice.dto.request.UniversityCreateRequest;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.service.UniversityService;
import lombok.RequiredArgsConstructor;
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
     * Lấy tất cả các trường đại học
     */
    @GetMapping
    public List<UniversityResponse> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    /**
     * Lấy trường đại học theo ID
     */
    @GetMapping("/id/{id}")
    public UniversityResponse getUniversityById(@PathVariable Long id) {
        return universityService.getUniversityById(id);
    }

    /**
     * Lấy trường đại học theo slug
     */

    @GetMapping("/slug/{slug}")
    public UniversityResponse getUniversityBySlug(@PathVariable String slug) {
        return universityService.getUniversityBySlug(slug);
    }

    /**
     * Tạo mới trường đại học
     */
    @PostMapping
    public UniversityResponse createUniversity(@RequestBody UniversityCreateRequest request) {
        return universityService.createUniversity(request);
    }

    /**
     * Cập nhật thông tin trường đại học
     */
    @PutMapping("/{id}")
    public UniversityResponse updateUniversity(@PathVariable Long id,
                                               @RequestBody UniversityCreateRequest request) {
        return universityService.updateUniversity(id, request);
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

    // === Slug-based endpoints (new) ===

    /**
     * Lấy tất cả các trường đại học (slug-based)
     */
    @GetMapping("/universities")
    public List<UniversityResponse> getAllUniversitiesSlug() {
        return universityService.getAllUniversities();
    }

    /**
     * Lấy trường đại học theo slug (slug-based)
     */
    @GetMapping("/universities/{slug}")
    public UniversityResponse getUniversityBySlugSlug(@PathVariable String slug) {
        return universityService.getUniversityBySlug(slug);
    }

    /**
     * Tạo mới trường đại học (slug-based)
     */
    @PostMapping("/universities")
    public UniversityResponse createUniversitySlug(@RequestBody UniversityCreateRequest request) {
        return universityService.createUniversity(request);
    }

    /**
     * Cập nhật thông tin trường đại học theo slug (slug-based)
     */
    @PutMapping("/universities/{slug}")
    public UniversityResponse updateUniversityBySlug(@PathVariable String slug, @RequestBody UniversityCreateRequest request) {
        return universityService.updateUniversityBySlug(slug, request);
    }

    /**
     * Xóa trường đại học theo slug (slug-based)
     */
    @DeleteMapping("/universities/{slug}")
    public void deleteUniversityBySlugSlug(@PathVariable String slug) {
        universityService.deleteUniversityBySlug(slug);
    }
}
