package com.studydocs.modules.academic.service.impl;

import com.studydocs.modules.academic.dto.AcademicDtos;
import com.studydocs.modules.academic.entity.DepartmentEntity;
import com.studydocs.modules.academic.entity.FacultyEntity;
import com.studydocs.modules.academic.entity.SubjectEntity;
import com.studydocs.modules.academic.entity.UniversityEntity;
import com.studydocs.modules.academic.repository.DepartmentRepository;
import com.studydocs.modules.academic.repository.FacultyRepository;
import com.studydocs.modules.academic.repository.SubjectRepository;
import com.studydocs.modules.academic.repository.UniversityRepository;
import com.studydocs.modules.academic.service.AcademicService;
import com.studydocs.shared.exception.AppException;
import com.studydocs.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicServiceImpl implements AcademicService {

    private final UniversityRepository universityRepository;
    private final SubjectRepository subjectRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDtos.UniversityDto> getAllUniversities() {
        return universityRepository.findAll().stream()
                .map(this::toUniversityDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDtos.UniversityDto> filterUniversities(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllUniversities();
        }
        return universityRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(this::toUniversityDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDtos.UniversityDto getUniversityById(Long id) {
        UniversityEntity entity = universityRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACADEMIC_NOT_FOUND, "University not found"));
        return toUniversityDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDtos.FacultyDto> filterFaculties(Long universityId, String keyword) {
        if (universityId != null) {
            return facultyRepository.findByUniversityId(universityId).stream()
                    .map(this::toFacultyDto)
                    .toList();
        }
        if (keyword != null && !keyword.isBlank()) {
            return facultyRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(keyword, keyword).stream()
                    .map(this::toFacultyDto)
                    .toList();
        }
        return facultyRepository.findAll().stream()
                .map(this::toFacultyDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDtos.FacultyDto getFacultyById(Long id) {
        FacultyEntity entity = facultyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACADEMIC_NOT_FOUND, "Faculty not found"));
        return toFacultyDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDtos.DepartmentDto> filterDepartments(Long facultyId, String keyword) {
        if (facultyId != null) {
            return departmentRepository.findByFacultyId(facultyId).stream()
                    .map(this::toDepartmentDto)
                    .toList();
        }
        if (keyword != null && !keyword.isBlank()) {
            return departmentRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(keyword, keyword).stream()
                    .map(this::toDepartmentDto)
                    .toList();
        }
        return departmentRepository.findAll().stream()
                .map(this::toDepartmentDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDtos.DepartmentDto getDepartmentById(Long id) {
        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACADEMIC_NOT_FOUND, "Department not found"));
        return toDepartmentDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDtos.SubjectDto> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::toSubjectDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDtos.SubjectDto> filterSubjects(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return getAllSubjects();
        }
        return subjectRepository.findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(this::toSubjectDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDtos.SubjectDto getSubjectById(Long id) {
        SubjectEntity entity = subjectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ACADEMIC_NOT_FOUND, "Subject not found"));
        return toSubjectDto(entity);
    }

    private AcademicDtos.UniversityDto toUniversityDto(UniversityEntity entity) {
        return AcademicDtos.UniversityDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .englishName(entity.getEnglishName())
                .logoUrl(entity.getLogoUrl())
                .build();
    }

    private AcademicDtos.FacultyDto toFacultyDto(FacultyEntity entity) {
        return AcademicDtos.FacultyDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .slug(entity.getSlug())
                .description(entity.getDescription())
                .universityId(entity.getUniversityId())
                .isActive(entity.getIsActive())
                .build();
    }

    private AcademicDtos.DepartmentDto toDepartmentDto(DepartmentEntity entity) {
        return AcademicDtos.DepartmentDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .slug(entity.getSlug())
                .description(entity.getDescription())
                .facultyId(entity.getFacultyId())
                .isActive(entity.getIsActive())
                .build();
    }

    private AcademicDtos.SubjectDto toSubjectDto(SubjectEntity entity) {
        return AcademicDtos.SubjectDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .universityId(entity.getUniversityId())
                .build();
    }
}
