package com.example.academicservice.service;

import com.example.academicservice.dto.request.MajorCreateRequest;
import com.example.academicservice.dto.request.MajorUpdateRequest;
import com.example.academicservice.dto.response.MajorResponse;
import com.example.academicservice.entity.Department;
import com.example.academicservice.entity.Major;
import com.example.academicservice.exception.DuplicateResourceException;
import com.example.academicservice.exception.ResourceNotFoundException;
import com.example.academicservice.mapper.MajorMapper;
import com.example.academicservice.repository.DepartmentRepository;
import com.example.academicservice.repository.MajorRepository;
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer để xử lý business logic cho Major entity
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MajorService {

    private final MajorRepository majorRepository;
    private final DepartmentRepository departmentRepository;
    private final MajorMapper majorMapper;

    // === ID-based methods ===

    /**
     * Lấy tất cả các ngành theo ID bộ môn
     */
    @Transactional(readOnly = true)
    public List<MajorResponse> getAllMajorsByDepartmentId(Long departmentId) {
        log.info("Fetching all majors for department id: {}", departmentId);
        return majorRepository.findByDepartmentId(departmentId).stream()
                .map(majorMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy các ngành đang active theo ID bộ môn
     */
    @Transactional(readOnly = true)
    public List<MajorResponse> getActiveMajorsByDepartmentId(Long departmentId) {
        log.info("Fetching active majors for department id: {}", departmentId);
        return majorRepository.findByDepartmentIdAndIsActive(departmentId, true).stream()
                .map(majorMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin ngành theo ID
     */
    @Transactional(readOnly = true)
    public MajorResponse getMajorById(Long id) {
        log.info("Fetching major with id: {}", id);
        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id", id));
        return majorMapper.toResponse(major);
    }

    /**
     * Lấy thông tin ngành theo slug trong một bộ môn
     */
    @Transactional(readOnly = true)
    public MajorResponse getMajorBySlug(Long departmentId, String slug) {
        log.info("Fetching major with slug: {} in department: {}", slug, departmentId);
        Major major = majorRepository.findByDepartmentIdAndSlug(departmentId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", slug));
        return majorMapper.toResponse(major);
    }

    /**
     * Tạo mới ngành
     */
    public MajorResponse createMajor(MajorCreateRequest request) {
        log.info("Creating major with name: {} for department id: {}", request.getName(), request.getDepartmentId());

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));

        String slug = StringUtil.toSlug(request.getName());
        if (majorRepository.existsByDepartmentIdAndSlug(department.getId(), slug)) {
            throw new DuplicateResourceException("Ngành với slug: " + slug + " đã tồn tại trong bộ môn này");
        }

        Major major = majorMapper.toEntity(request);
        major.setSlug(slug);
        major.setDepartment(department);
        major.setIsActive(true);

        Major savedMajor = majorRepository.save(major);
        log.info("Major created successfully with id: {}", savedMajor.getId());

        return majorMapper.toResponse(savedMajor);
    }

    /**
     * Cập nhật thông tin ngành theo ID
     */
    public MajorResponse updateMajor(Long id, MajorUpdateRequest request) {
        log.info("Updating major with id: {}", id);

        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id", id));

        return updateMajorInternal(major, request);
    }

    /**
     * Xóa ngành theo ID
     */
    public void deleteMajorById(Long id) {
        log.info("Deleting major with id: {}", id);

        if (!majorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Major", "id", id);
        }

        majorRepository.deleteById(id);
        log.info("Major deleted successfully with id: {}", id);
    }

    /**
     * Xóa ngành theo slug trong bộ môn
     */
    public void deleteMajorBySlug(Long departmentId, String slug) {
        log.info("Deleting major with slug: {} in department: {}", slug, departmentId);

        Major major = majorRepository.findByDepartmentIdAndSlug(departmentId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", slug));

        majorRepository.delete(major);
        log.info("Major deleted successfully with slug: {}", slug);
    }

    // === Slug-based methods ===

    /**
     * Lấy tất cả các ngành theo chuỗi slug (university -> faculty -> department)
     */
    @Transactional(readOnly = true)
    public List<MajorResponse> getAllMajorsByUniversityFacultyAndDepartmentSlug(String universitySlug,
                                                                               String facultySlug,
                                                                               String departmentSlug) {
        log.info("Fetching all majors for university slug: {}, faculty slug: {}, department slug: {}",
                universitySlug, facultySlug, departmentSlug);
        return majorRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlug(universitySlug, facultySlug, departmentSlug)
                .stream()
                .map(majorMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy các ngành đang active theo chuỗi slug
     */
    @Transactional(readOnly = true)
    public List<MajorResponse> getActiveMajorsByUniversityFacultyAndDepartmentSlug(String universitySlug,
                                                                                   String facultySlug,
                                                                                   String departmentSlug) {
        log.info("Fetching active majors for university slug: {}, faculty slug: {}, department slug: {}",
                universitySlug, facultySlug, departmentSlug);
        return majorRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlugAndIsActive(universitySlug, facultySlug, departmentSlug, true)
                .stream()
                .map(majorMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin ngành theo chuỗi slug đầy đủ (university -> faculty -> department -> major)
     */
    @Transactional(readOnly = true)
    public MajorResponse getMajorByUniversityFacultyDepartmentAndMajorSlug(String universitySlug,
                                                                           String facultySlug,
                                                                           String departmentSlug,
                                                                           String majorSlug) {
        log.info("Fetching major with university slug: {}, faculty slug: {}, department slug: {}, major slug: {}",
                universitySlug, facultySlug, departmentSlug, majorSlug);

        Major major = majorRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlugAndMajorSlug(universitySlug, facultySlug, departmentSlug, majorSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", majorSlug));

        return majorMapper.toResponse(major);
    }

    /**
     * Tạo mới ngành bằng chuỗi slug (university -> faculty -> department)
     */
    public MajorResponse createMajorByUniversityFacultyAndDepartmentSlug(String universitySlug,
                                                                         String facultySlug,
                                                                         String departmentSlug,
                                                                         MajorCreateRequest request) {
        log.info("Creating major with name: {} for university slug: {}, faculty slug: {}, department slug: {}",
                request.getName(), universitySlug, facultySlug, departmentSlug);

        Department department = departmentRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlug(universitySlug, facultySlug, departmentSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", departmentSlug));

        String slug = StringUtil.toSlug(request.getName());
        if (majorRepository.existsByDepartmentIdAndSlug(department.getId(), slug)) {
            throw new DuplicateResourceException("Ngành với slug: " + slug + " đã tồn tại trong bộ môn này");
        }

        Major major = majorMapper.toEntity(request);
        major.setSlug(slug);
        major.setDepartment(department);
        major.setIsActive(true);

        Major savedMajor = majorRepository.save(major);
        log.info("Major created successfully with id: {}", savedMajor.getId());

        return majorMapper.toResponse(savedMajor);
    }

    /**
     * Cập nhật thông tin ngành bằng chuỗi slug đầy đủ
     */
    public MajorResponse updateMajorByUniversityFacultyDepartmentAndMajorSlug(String universitySlug,
                                                                              String facultySlug,
                                                                              String departmentSlug,
                                                                              String majorSlug,
                                                                              MajorUpdateRequest request) {
        log.info("Updating major with university slug: {}, faculty slug: {}, department slug: {}, major slug: {}",
                universitySlug, facultySlug, departmentSlug, majorSlug);

        Major major = majorRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlugAndMajorSlug(universitySlug, facultySlug, departmentSlug, majorSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", majorSlug));

        return updateMajorInternal(major, request);
    }

    /**
     * Xóa ngành bằng chuỗi slug đầy đủ
     */
    public void deleteMajorByUniversityFacultyDepartmentAndMajorSlug(String universitySlug,
                                                                     String facultySlug,
                                                                     String departmentSlug,
                                                                     String majorSlug) {
        log.info("Deleting major with university slug: {}, faculty slug: {}, department slug: {}, major slug: {}",
                universitySlug, facultySlug, departmentSlug, majorSlug);

        Major major = majorRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlugAndMajorSlug(universitySlug, facultySlug, departmentSlug, majorSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", majorSlug));

        majorRepository.delete(major);
        log.info("Major deleted successfully with slug: {}", majorSlug);
    }

    /**
     * Internal method để xử lý logic update chung cho Major
     */
    private MajorResponse updateMajorInternal(Major major, MajorUpdateRequest request) {
        if (request.getName() != null && !request.getName().equals(major.getName())) {
            String newSlug = StringUtil.toSlug(request.getName());
            Long departmentId = major.getDepartment().getId();

            if (majorRepository.existsByDepartmentIdAndSlug(departmentId, newSlug)) {
                throw new DuplicateResourceException("Slug: " + newSlug + " đã tồn tại");
            }

            major.setSlug(newSlug);
        }

        majorMapper.updateEntityFromRequest(request, major);

        Major updatedMajor = majorRepository.save(major);
        log.info("Major updated successfully with id: {}", updatedMajor.getId());

        return majorMapper.toResponse(updatedMajor);
    }
}

