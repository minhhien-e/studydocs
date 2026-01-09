package com.example.academicservice.controller;

import com.example.academicservice.dto.request.FacultyCreateRequest;
import com.example.academicservice.dto.request.FacultyUpdateRequest;
import com.example.academicservice.dto.response.FacultyResponse;
import com.example.academicservice.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller để xử lý các HTTP requests liên quan đến Faculty
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    /**
     * Lấy thông tin khoa theo ID
     */
    @GetMapping("/id/{id}")
    public FacultyResponse getFacultyById(@PathVariable UUID id) {
        return facultyService.getFacultyById(id);
    }

    /**
     * Tạo mới khoa
     */
    @PostMapping
    public FacultyResponse createFaculty(@RequestBody FacultyCreateRequest request) {
        return facultyService.createFaculty(request);
    }

    /**
     * Cập nhật thông tin khoa theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    @PutMapping("/id/{id}")
    public FacultyResponse updateFacultyById(@PathVariable UUID id,
                                            @RequestParam UUID universityId,
                                            @RequestBody FacultyUpdateRequest request) {
        return facultyService.updateFaculty(id, universityId, request);
    }

    /**
     * Cập nhật thông tin khoa theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    @PutMapping("/slug/{slug}")
    public FacultyResponse updateFacultyBySlug(@PathVariable String slug,
                                              @RequestParam UUID universityId,
                                              @RequestBody FacultyUpdateRequest request) {
        return facultyService.updateFacultyBySlug(universityId, slug, request);
    }

    /**
     * Xóa khoa theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    @DeleteMapping("/id/{id}")
    public void deleteFacultyById(@PathVariable UUID id, @RequestParam UUID universityId) {
        facultyService.deleteFacultyById(id, universityId);
    }

    /**
     * Xóa khoa theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    @DeleteMapping("/slug/{slug}")
    public void deleteFacultyBySlug(@PathVariable String slug, @RequestParam UUID universityId) {
        facultyService.deleteFacultyBySlug(universityId, slug);
    }

    /**
     * Filter faculties với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách khoa
     */
    @GetMapping("/filter")
    public List<FacultyResponse> filterFaculties(
            @RequestParam(required = false) UUID universityId,
            @RequestParam(required = false) String universitySlug,
            @RequestParam(required = false) Boolean isActive) {
        return facultyService.filter(universityId, universitySlug, isActive);
    }
}

