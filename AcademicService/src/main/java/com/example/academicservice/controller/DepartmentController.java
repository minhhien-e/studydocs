package com.example.academicservice.controller;

import com.example.academicservice.dto.request.DepartmentCreateRequest;
import com.example.academicservice.dto.request.DepartmentUpdateRequest;
import com.example.academicservice.dto.response.DepartmentResponse;
import com.example.academicservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller để xử lý các HTTP requests liên quan đến Department
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DepartmentController {

    private final DepartmentService departmentService;

    // === ID-based endpoints (existing) ===

    /**
     * Lấy tất cả các bộ môn theo ID khoa
     */
    @GetMapping("/departments/faculty/{facultyId}")
    public List<DepartmentResponse> getAllDepartmentsByFacultyId(@PathVariable Long facultyId) {
        return departmentService.getAllDepartmentsByFacultyId(facultyId);
    }

    /**
     * Lấy các bộ môn đang active theo ID khoa
     */
    @GetMapping("/departments/faculty/{facultyId}/active")
    public List<DepartmentResponse> getActiveDepartmentsByFacultyId(@PathVariable Long facultyId) {
        return departmentService.getActiveDepartmentsByFacultyId(facultyId);
    }

    /**
     * Lấy thông tin bộ môn theo ID
     */
    @GetMapping("/departments/id/{id}")
    public DepartmentResponse getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    /**
     * Lấy thông tin bộ môn theo slug trong một khoa
     */
    @GetMapping("/departments/faculty/{facultyId}/slug/{slug}")
    public DepartmentResponse getDepartmentBySlug(@PathVariable Long facultyId, @PathVariable String slug) {
        return departmentService.getDepartmentBySlug(facultyId, slug);
    }

    /**
     * Tạo mới bộ môn
     */
    @PostMapping("/departments")
    public DepartmentResponse createDepartment(@RequestBody DepartmentCreateRequest request) {
        return departmentService.createDepartment(request);
    }

    /**
     * Cập nhật thông tin bộ môn theo ID
     */
    @PutMapping("/departments/{id}")
    public DepartmentResponse updateDepartment(@PathVariable Long id, @RequestBody DepartmentUpdateRequest request) {
        return departmentService.updateDepartment(id, request);
    }

    /**
     * Xóa bộ môn theo ID
     */
    @DeleteMapping("/departments/id/{id}")
    public void deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartmentById(id);
    }

    /**
     * Xóa bộ môn theo slug trong một khoa
     */
    @DeleteMapping("/departments/faculty/{facultyId}/slug/{slug}")
    public void deleteDepartmentBySlug(@PathVariable Long facultyId, @PathVariable String slug) {
        departmentService.deleteDepartmentBySlug(facultyId, slug);
    }

    // === Slug-based endpoints (new) ===

    /**
     * Lấy tất cả các bộ môn theo university slug + faculty slug
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments")
    public List<DepartmentResponse> getAllDepartmentsByUniversitySlugAndFacultySlug(@PathVariable String universitySlug,
                                                                                    @PathVariable String facultySlug) {
        return departmentService.getAllDepartmentsByUniversitySlugAndFacultySlug(universitySlug, facultySlug);
    }

    /**
     * Lấy các bộ môn đang active theo university slug + faculty slug
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/active")
    public List<DepartmentResponse> getActiveDepartmentsByUniversitySlugAndFacultySlug(@PathVariable String universitySlug,
                                                                                       @PathVariable String facultySlug) {
        return departmentService.getActiveDepartmentsByUniversitySlugAndFacultySlug(universitySlug, facultySlug);
    }

    /**
     * Lấy thông tin bộ môn theo chuỗi slug (university -> faculty -> department)
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}")
    public DepartmentResponse getDepartmentByUniversityFacultyAndDepartmentSlug(@PathVariable String universitySlug,
                                                                                @PathVariable String facultySlug,
                                                                                @PathVariable String departmentSlug) {
        return departmentService.getDepartmentByUniversityFacultyAndDepartmentSlug(universitySlug, facultySlug, departmentSlug);
    }

    /**
     * Tạo mới bộ môn bằng chuỗi slug (university -> faculty)
     */
    @PostMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments")
    public DepartmentResponse createDepartmentByUniversitySlugAndFacultySlug(@PathVariable String universitySlug,
                                                                             @PathVariable String facultySlug,
                                                                             @RequestBody DepartmentCreateRequest request) {
        return departmentService.createDepartmentByUniversitySlugAndFacultySlug(universitySlug, facultySlug, request);
    }

    /**
     * Cập nhật thông tin bộ môn bằng chuỗi slug đầy đủ
     */
    @PutMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}")
    public DepartmentResponse updateDepartmentByUniversityFacultyAndDepartmentSlug(@PathVariable String universitySlug,
                                                                                   @PathVariable String facultySlug,
                                                                                   @PathVariable String departmentSlug,
                                                                                   @RequestBody DepartmentUpdateRequest request) {
        return departmentService.updateDepartmentByUniversityFacultyAndDepartmentSlug(universitySlug, facultySlug, departmentSlug, request);
    }

    /**
     * Xóa bộ môn bằng chuỗi slug đầy đủ
     */
    @DeleteMapping("/universities/{universitySlug}/faculties/{facultySlug}/departments/{departmentSlug}")
    public void deleteDepartmentByUniversityFacultyAndDepartmentSlug(@PathVariable String universitySlug,
                                                                     @PathVariable String facultySlug,
                                                                     @PathVariable String departmentSlug) {
        departmentService.deleteDepartmentByUniversityFacultyAndDepartmentSlug(universitySlug, facultySlug, departmentSlug);
    }
}
