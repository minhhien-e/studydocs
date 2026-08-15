package com.studydocs.modules.academic.controller;

import com.studydocs.modules.academic.dto.AcademicDtos;
import com.studydocs.modules.academic.service.AcademicService;
import com.studydocs.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education/academics")
@RequiredArgsConstructor
public class AcademicController {

    private final AcademicService academicService;

    @GetMapping("/universities")
    public ApiResponse<List<AcademicDtos.UniversityDto>> getAllUniversities() {
        return ApiResponse.success(academicService.getAllUniversities());
    }

    @GetMapping("/universities/filter")
    public ApiResponse<List<AcademicDtos.UniversityDto>> filterUniversities(@RequestParam(value = "keyword", required = false) String keyword) {
        return ApiResponse.success(academicService.filterUniversities(keyword));
    }

    @GetMapping("/public/universities/id")
    public ApiResponse<AcademicDtos.UniversityDto> getUniversityById(@RequestParam("id") Long id) {
        return ApiResponse.success(academicService.getUniversityById(id));
    }

    @GetMapping("/faculties/filter")
    public ApiResponse<List<AcademicDtos.FacultyDto>> filterFaculties(
            @RequestParam(value = "universityId", required = false) Long universityId,
            @RequestParam(value = "keyword", required = false) String keyword) {
        return ApiResponse.success(academicService.filterFaculties(universityId, keyword));
    }

    @GetMapping("/faculties/id/{id}")
    public ApiResponse<AcademicDtos.FacultyDto> getFacultyById(@PathVariable Long id) {
        return ApiResponse.success(academicService.getFacultyById(id));
    }

    @GetMapping("/departments/filter")
    public ApiResponse<List<AcademicDtos.DepartmentDto>> filterDepartments(
            @RequestParam(value = "facultyId", required = false) Long facultyId,
            @RequestParam(value = "keyword", required = false) String keyword) {
        return ApiResponse.success(academicService.filterDepartments(facultyId, keyword));
    }

    @GetMapping("/departments/id/{id}")
    public ApiResponse<AcademicDtos.DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ApiResponse.success(academicService.getDepartmentById(id));
    }

    @GetMapping("/subjects")
    public ApiResponse<List<AcademicDtos.SubjectDto>> getAllSubjects() {
        return ApiResponse.success(academicService.getAllSubjects());
    }

    @GetMapping("/subjects/filter")
    public ApiResponse<List<AcademicDtos.SubjectDto>> filterSubjects(@RequestParam(value = "keyword", required = false) String keyword) {
        return ApiResponse.success(academicService.filterSubjects(keyword));
    }

    @GetMapping("/public/subjects/id")
    public ApiResponse<AcademicDtos.SubjectDto> getSubjectById(@RequestParam("id") Long id) {
        return ApiResponse.success(academicService.getSubjectById(id));
    }
}
