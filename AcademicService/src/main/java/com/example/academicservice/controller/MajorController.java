package com.example.academicservice.controller;

import com.example.academicservice.dto.request.MajorCreateRequest;
import com.example.academicservice.dto.request.MajorUpdateRequest;
import com.example.academicservice.dto.response.MajorResponse;
import com.example.academicservice.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller để xử lý các HTTP requests liên quan đến Major
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MajorController {

    private final MajorService majorService;

    // === ID-based endpoints (existing) ===

    /**
     * Lấy tất cả các ngành theo ID bộ môn
     */
    @GetMapping("/majors/department/{departmentId}")
    public List<MajorResponse> getAllMajorsByDepartmentId(@PathVariable Long departmentId) {
        return majorService.getAllMajorsByDepartmentId(departmentId);
    }

    /**
     * Lấy các ngành đang active theo ID bộ môn
     */
    @GetMapping("/majors/department/{departmentId}/active")
    public List<MajorResponse> getActiveMajorsByDepartmentId(@PathVariable Long departmentId) {
        return majorService.getActiveMajorsByDepartmentId(departmentId);
    }

    /**
     * Lấy thông tin ngành theo ID
     */
    @GetMapping("/majors/id/{id}")
    public MajorResponse getMajorById(@PathVariable Long id) {
        return majorService.getMajorById(id);
    }

    /**
     * Lấy thông tin ngành theo slug trong một bộ môn
     */
    @GetMapping("/majors/department/{departmentId}/slug/{slug}")
    public MajorResponse getMajorBySlug(@PathVariable Long departmentId, @PathVariable String slug) {
        return majorService.getMajorBySlug(departmentId, slug);
    }

    /**
     * Tạo mới ngành
     */
    @PostMapping("/majors")
    public MajorResponse createMajor(@RequestBody MajorCreateRequest request) {
        return majorService.createMajor(request);
    }

    /**
     * Cập nhật thông tin ngành theo ID
     */
    @PutMapping("/majors/{id}")
    public MajorResponse updateMajor(@PathVariable Long id, @RequestBody MajorUpdateRequest request) {
        return majorService.updateMajor(id, request);
    }

    /**
     * Xóa ngành theo ID
     */
    @DeleteMapping("/majors/id/{id}")
    public void deleteMajorById(@PathVariable Long id) {
        majorService.deleteMajorById(id);
    }

    /**
     * Xóa ngành theo slug trong một bộ môn
     */
    @DeleteMapping("/majors/department/{departmentId}/slug/{slug}")
    public void deleteMajorBySlug(@PathVariable Long departmentId, @PathVariable String slug) {
        majorService.deleteMajorBySlug(departmentId, slug);
    }

    // === Slug-based endpoints (new) ===

    /**
     * Lấy tất cả các ngành theo chuỗi slug (university -> faculty -> department)
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}/majors")
    public List<MajorResponse> getAllMajorsByUniversityFacultyAndDepartmentSlug(@PathVariable String universitySlug,
                                                                                @PathVariable String facultySlug,
                                                                                @PathVariable String departmentSlug) {
        return majorService.getAllMajorsByUniversityFacultyAndDepartmentSlug(universitySlug, facultySlug, departmentSlug);
    }

    /**
     * Lấy các ngành đang active theo chuỗi slug
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}/majors/active")
    public List<MajorResponse> getActiveMajorsByUniversityFacultyAndDepartmentSlug(@PathVariable String universitySlug,
                                                                                   @PathVariable String facultySlug,
                                                                                   @PathVariable String departmentSlug) {
        return majorService.getActiveMajorsByUniversityFacultyAndDepartmentSlug(universitySlug, facultySlug, departmentSlug);
    }

    /**
     * Lấy thông tin ngành theo chuỗi slug đầy đủ (university -> faculty -> department -> major)
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}/majors/{majorSlug}")
    public MajorResponse getMajorByUniversityFacultyDepartmentAndMajorSlug(@PathVariable String universitySlug,
                                                                           @PathVariable String facultySlug,
                                                                           @PathVariable String departmentSlug,
                                                                           @PathVariable String majorSlug) {
        return majorService.getMajorByUniversityFacultyDepartmentAndMajorSlug(universitySlug, facultySlug, departmentSlug, majorSlug);
    }

    /**
     * Tạo mới ngành bằng chuỗi slug (university -> faculty -> department)
     */
    @PostMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}/majors")
    public MajorResponse createMajorByUniversityFacultyAndDepartmentSlug(@PathVariable String universitySlug,
                                                                         @PathVariable String facultySlug,
                                                                         @PathVariable String departmentSlug,
                                                                         @RequestBody MajorCreateRequest request) {
        return majorService.createMajorByUniversityFacultyAndDepartmentSlug(universitySlug, facultySlug, departmentSlug, request);
    }

    /**
     * Cập nhật thông tin ngành bằng chuỗi slug đầy đủ
     */
    @PutMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}/majors/{majorSlug}")
    public MajorResponse updateMajorByUniversityFacultyDepartmentAndMajorSlug(@PathVariable String universitySlug,
                                                                              @PathVariable String facultySlug,
                                                                              @PathVariable String departmentSlug,
                                                                              @PathVariable String majorSlug,
                                                                              @RequestBody MajorUpdateRequest request) {
        return majorService.updateMajorByUniversityFacultyDepartmentAndMajorSlug(universitySlug, facultySlug, departmentSlug, majorSlug, request);
    }

    /**
     * Xóa ngành bằng chuỗi slug đầy đủ
     */
    @DeleteMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}/majors/{majorSlug}")
    public void deleteMajorByUniversityFacultyDepartmentAndMajorSlug(@PathVariable String universitySlug,
                                                                     @PathVariable String facultySlug,
                                                                     @PathVariable String departmentSlug,
                                                                     @PathVariable String majorSlug) {
        majorService.deleteMajorByUniversityFacultyDepartmentAndMajorSlug(universitySlug, facultySlug, departmentSlug, majorSlug);
    }
}

