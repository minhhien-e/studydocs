package com.example.academicservice.controller;

import com.example.academicservice.dto.request.DepartmentCreateRequest;
import com.example.academicservice.dto.request.DepartmentUpdateRequest;
import com.example.academicservice.dto.response.DepartmentResponse;
import com.example.academicservice.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

/**
 * Controller để xử lý các HTTP requests liên quan đến Department
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * Lấy thông tin bộ môn theo ID
     */
    @GetMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public DepartmentResponse getDepartmentById(@PathVariable UUID id) {
        return departmentService.getDepartmentById(id);
    }

    /**
     * Tạo mới bộ môn
     */

    @PostMapping
//    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public DepartmentResponse createDepartment(@Valid @RequestBody DepartmentCreateRequest request) {
        return departmentService.createDepartment(request);
    }

    /**
     * Cập nhật thông tin bộ môn theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @PutMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public DepartmentResponse updateDepartmentById(@PathVariable UUID id,
                                                    @RequestParam UUID universityId,
                                                    @Valid @RequestBody DepartmentUpdateRequest request) {
        return departmentService.updateDepartment(id, universityId, request);
    }

    /**
     * Cập nhật thông tin bộ môn theo slug
     * Bắt buộc phải có universityId và facultyId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @PutMapping("/slug/{slug}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public DepartmentResponse updateDepartmentBySlug(@PathVariable String slug,
                                                     @RequestParam UUID universityId,
                                                     @RequestParam UUID facultyId,
                                                     @Valid @RequestBody DepartmentUpdateRequest request) {
        return departmentService.updateDepartmentBySlug(universityId, facultyId, slug, request);
    }

    /**
     * Xóa bộ môn theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @DeleteMapping("/id/{id}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public void deleteDepartmentById(@PathVariable UUID id, @RequestParam UUID universityId) {
        departmentService.deleteDepartmentById(id, universityId);
    }

    /**
     * Xóa bộ môn theo slug
     * Bắt buộc phải có universityId và facultyId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    @DeleteMapping("/slug/{slug}")
    @PreAuthorize("hasAuthority('SCOPE_WRITE_USER') and hasRole('ADMIN')")
    public void deleteDepartmentBySlug(@PathVariable String slug,
                                      @RequestParam UUID universityId,
                                      @RequestParam UUID facultyId) {
        departmentService.deleteDepartmentBySlug(universityId, facultyId, slug);
    }

    /**
     * Filter departments với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param facultyId - ID khoa (optional)
     * @param facultySlug - Slug khoa (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách bộ môn
     */
    @GetMapping("/filter")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public List<DepartmentResponse> filterDepartments(
            @RequestParam(required = false) UUID universityId,
            @RequestParam(required = false) String universitySlug,
            @RequestParam(required = false) UUID facultyId,
            @RequestParam(required = false) String facultySlug,
            @RequestParam(required = false) Boolean isActive) {
        return departmentService.filter(universityId, universitySlug, facultyId, facultySlug, isActive);
    }
}
