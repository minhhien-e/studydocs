package com.studydocs.modules.academic.service;

import com.studydocs.modules.academic.dto.AcademicDtos;

import java.util.List;

public interface AcademicService {
    List<AcademicDtos.UniversityDto> getAllUniversities();
    List<AcademicDtos.UniversityDto> filterUniversities(String keyword);
    AcademicDtos.UniversityDto getUniversityById(Long id);
    List<AcademicDtos.FacultyDto> filterFaculties(Long universityId, String keyword);
    AcademicDtos.FacultyDto getFacultyById(Long id);
    List<AcademicDtos.DepartmentDto> filterDepartments(Long facultyId, String keyword);
    AcademicDtos.DepartmentDto getDepartmentById(Long id);
    List<AcademicDtos.SubjectDto> getAllSubjects();
    List<AcademicDtos.SubjectDto> filterSubjects(String keyword);
    AcademicDtos.SubjectDto getSubjectById(Long id);
}
