package com.example.academicservice.controller;

import com.example.academicservice.dto.request.FacultyCreateRequest;
import com.example.academicservice.dto.request.FacultyUpdateRequest;
import com.example.academicservice.dto.response.FacultyResponse;
import com.example.academicservice.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller để xử lý các HTTP requests liên quan đến Faculty
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FacultyController {

    private final FacultyService facultyService;

    // === ID-based endpoints (existing) ===

    /**
     * Lấy tất cả các khoa theo ID trường đại học
     */
    @GetMapping("/faculties/university/{universityId}")
    public List<FacultyResponse> getAllFacultiesByUniversityId(@PathVariable Long universityId) {
        return facultyService.getAllFacultiesByUniversityId(universityId);
    }

    /**
     * Lấy các khoa đang active theo ID trường đại học
     */
    @GetMapping("/faculties/university/{universityId}/active")
    public List<FacultyResponse> getActiveFacultiesByUniversityId(@PathVariable Long universityId) {
        return facultyService.getActiveFacultiesByUniversityId(universityId);
    }

    /**
     * Lấy thông tin khoa theo ID
     */
    @GetMapping("/faculties/id/{id}")
    public FacultyResponse getFacultyById(@PathVariable Long id) {
        return facultyService.getFacultyById(id);
    }

    /**
     * Lấy thông tin khoa theo slug trong một trường đại học
     */
    @GetMapping("/faculties/university/{universityId}/slug/{slug}")
    public FacultyResponse getFacultyBySlug(@PathVariable Long universityId, @PathVariable String slug) {
        return facultyService.getFacultyBySlug(universityId, slug);
    }

    /**
     * Tạo mới khoa
     */
    @PostMapping("/faculties")
    public FacultyResponse createFaculty(@RequestBody FacultyCreateRequest request) {
        return facultyService.createFaculty(request);
    }

    /**
     * Cập nhật thông tin khoa
     */
    @PutMapping("/faculties/{id}")
    public FacultyResponse updateFaculty(@PathVariable Long id, @RequestBody FacultyUpdateRequest request) {
        return facultyService.updateFaculty(id, request);
    }

    /**
     * Xóa khoa theo ID
     */
    @DeleteMapping("/faculties/id/{id}")
    public void deleteFacultyById(@PathVariable Long id) {
        facultyService.deleteFacultyById(id);
    }

    /**
     * Xóa khoa theo slug
     */
    @DeleteMapping("/faculties/university/{universityId}/slug/{slug}")
    public void deleteFacultyBySlug(@PathVariable Long universityId, @PathVariable String slug) {
        facultyService.deleteFacultyBySlug(universityId, slug);
    }

    // === Slug-based endpoints (new) ===

    /**
     * Lấy tất cả các khoa theo university slug
     */
    @GetMapping("/universities/{universitySlug}/faculties")
    public List<FacultyResponse> getAllFacultiesByUniversitySlug(@PathVariable String universitySlug) {
        return facultyService.getAllFacultiesByUniversitySlug(universitySlug);
    }

    /**
     * Lấy các khoa đang active theo university slug
     */
    @GetMapping("/universities/{universitySlug}/faculties/active")
    public List<FacultyResponse> getActiveFacultiesByUniversitySlug(@PathVariable String universitySlug) {
        return facultyService.getActiveFacultiesByUniversitySlug(universitySlug);
    }

    /**
     * Lấy thông tin khoa theo university slug + faculty slug
     */
    @GetMapping("/universities/{universitySlug}/faculties/{facultySlug}")
    public FacultyResponse getFacultyByUniversitySlugAndFacultySlug(@PathVariable String universitySlug, 
                                                                    @PathVariable String facultySlug) {
        return facultyService.getFacultyByUniversitySlugAndFacultySlug(universitySlug, facultySlug);
    }

    /**
     * Tạo mới khoa bằng university slug
     */
    @PostMapping("/universities/{universitySlug}/faculties")
    public FacultyResponse createFacultyByUniversitySlug(@PathVariable String universitySlug, 
                                                        @RequestBody FacultyCreateRequest request) {
        return facultyService.createFacultyByUniversitySlug(universitySlug, request);
    }

    /**
     * Cập nhật thông tin khoa bằng university slug + faculty slug
     */
    @PutMapping("/universities/{universitySlug}/faculties/{facultySlug}")
    public FacultyResponse updateFacultyByUniversitySlugAndFacultySlug(@PathVariable String universitySlug, 
                                                                      @PathVariable String facultySlug, 
                                                                      @RequestBody FacultyUpdateRequest request) {
        return facultyService.updateFacultyByUniversitySlugAndFacultySlug(universitySlug, facultySlug, request);
    }

    /**
     * Xóa khoa theo university slug + faculty slug
     */
    @DeleteMapping("/universities/{universitySlug}/faculties/{facultySlug}")
    public void deleteFacultyByUniversitySlugAndFacultySlug(@PathVariable String universitySlug, 
                                                            @PathVariable String facultySlug) {
        facultyService.deleteFacultyByUniversitySlugAndFacultySlug(universitySlug, facultySlug);
    }
}

