package com.example.academicservice.service;

import com.example.academicservice.dto.request.DepartmentCreateRequest;
import com.example.academicservice.dto.request.DepartmentUpdateRequest;
import com.example.academicservice.dto.response.DepartmentResponse;
import com.example.academicservice.entity.Department;
import com.example.academicservice.entity.Faculty;
import com.example.academicservice.exception.DuplicateResourceException;
import com.example.academicservice.exception.ResourceNotFoundException;
import com.example.academicservice.mapper.DepartmentMapper;
import com.example.academicservice.repository.DepartmentRepository;
import com.example.academicservice.repository.FacultyRepository;
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer để xử lý business logic cho Department entity
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentMapper departmentMapper;

    // === ID-based methods ===

    /**
     * Lấy tất cả các bộ môn theo ID khoa
     */
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartmentsByFacultyId(Long facultyId) {
        log.info("Fetching all departments for faculty id: {}", facultyId);
        return departmentRepository.findByFacultyId(facultyId).stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy các bộ môn đang active theo ID khoa
     */
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getActiveDepartmentsByFacultyId(Long facultyId) {
        log.info("Fetching active departments for faculty id: {}", facultyId);
        return departmentRepository.findByFacultyIdAndIsActive(facultyId, true).stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin bộ môn theo ID
     */
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(Long id) {
        log.info("Fetching department with id: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
        return departmentMapper.toResponse(department);
    }

    /**
     * Lấy thông tin bộ môn theo slug trong một khoa
     */
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentBySlug(Long facultyId, String slug) {
        log.info("Fetching department with slug: {} in faculty: {}", slug, facultyId);
        Department department = departmentRepository.findByFacultyIdAndSlug(facultyId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", slug));
        return departmentMapper.toResponse(department);
    }

    /**
     * Tạo mới bộ môn
     */
    public DepartmentResponse createDepartment(DepartmentCreateRequest request) {
        log.info("Creating department with name: {} for faculty id: {}", request.getName(), request.getFacultyId());

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", request.getFacultyId()));

        String slug = StringUtil.toSlug(request.getName());
        if (departmentRepository.existsByFacultyIdAndSlug(faculty.getId(), slug)) {
            throw new DuplicateResourceException("Bộ môn với slug: " + slug + " đã tồn tại trong khoa này");
        }

        Department department = departmentMapper.toEntity(request);
        department.setSlug(slug);
        department.setFaculty(faculty);
        department.setIsActive(true);

        Department savedDepartment = departmentRepository.save(department);
        log.info("Department created successfully with id: {}", savedDepartment.getId());

        return departmentMapper.toResponse(savedDepartment);
    }

    /**
     * Cập nhật thông tin bộ môn theo ID
     */
    public DepartmentResponse updateDepartment(Long id, DepartmentUpdateRequest request) {
        log.info("Updating department with id: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        return updateDepartmentInternal(department, request);
    }

    /**
     * Xóa bộ môn theo ID
     */
    public void deleteDepartmentById(Long id) {
        log.info("Deleting department with id: {}", id);

        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department", "id", id);
        }

        departmentRepository.deleteById(id);
        log.info("Department deleted successfully with id: {}", id);
    }

    /**
     * Xóa bộ môn theo slug trong khoa
     */
    public void deleteDepartmentBySlug(Long facultyId, String slug) {
        log.info("Deleting department with slug: {} in faculty: {}", slug, facultyId);

        Department department = departmentRepository.findByFacultyIdAndSlug(facultyId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", slug));

        departmentRepository.delete(department);
        log.info("Department deleted successfully with slug: {}", slug);
    }

    // === Slug-based methods ===

    /**
     * Lấy tất cả các bộ môn theo university slug + faculty slug
     */
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getAllDepartmentsByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug) {
        log.info("Fetching all departments for university slug: {} and faculty slug: {}", universitySlug, facultySlug);
        return departmentRepository.findByUniversitySlugAndFacultySlug(universitySlug, facultySlug).stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy các bộ môn đang active theo university slug + faculty slug
     */
    @Transactional(readOnly = true)
    public List<DepartmentResponse> getActiveDepartmentsByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug) {
        log.info("Fetching active departments for university slug: {} and faculty slug: {}", universitySlug, facultySlug);
        return departmentRepository.findByUniversitySlugAndFacultySlugAndIsActive(universitySlug, facultySlug, true).stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin bộ môn theo chuỗi slug (university -> faculty -> department)
     */
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentByUniversityFacultyAndDepartmentSlug(String universitySlug, String facultySlug, String departmentSlug) {
        log.info("Fetching department with university slug: {}, faculty slug: {}, department slug: {}",
                universitySlug, facultySlug, departmentSlug);

        Department department = departmentRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlug(universitySlug, facultySlug, departmentSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", departmentSlug));

        return departmentMapper.toResponse(department);
    }

    /**
     * Tạo mới bộ môn bằng chuỗi slug (university -> faculty)
     */
    public DepartmentResponse createDepartmentByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug, DepartmentCreateRequest request) {
        log.info("Creating department with name: {} for university slug: {} and faculty slug: {}",
                request.getName(), universitySlug, facultySlug);

        Faculty faculty = facultyRepository.findByUniversitySlugAndFacultySlug(universitySlug, facultySlug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", facultySlug));

        String slug = StringUtil.toSlug(request.getName());
        if (departmentRepository.existsByFacultyIdAndSlug(faculty.getId(), slug)) {
            throw new DuplicateResourceException("Bộ môn với slug: " + slug + " đã tồn tại trong khoa này");
        }

        Department department = departmentMapper.toEntity(request);
        department.setSlug(slug);
        department.setFaculty(faculty);
        department.setIsActive(true);

        Department savedDepartment = departmentRepository.save(department);
        log.info("Department created successfully with id: {}", savedDepartment.getId());

        return departmentMapper.toResponse(savedDepartment);
    }

    /**
     * Cập nhật thông tin bộ môn bằng chuỗi slug (university -> faculty -> department)
     */
    public DepartmentResponse updateDepartmentByUniversityFacultyAndDepartmentSlug(String universitySlug,
                                                                                  String facultySlug,
                                                                                  String departmentSlug,
                                                                                  DepartmentUpdateRequest request) {
        log.info("Updating department with university slug: {}, faculty slug: {}, department slug: {}",
                universitySlug, facultySlug, departmentSlug);

        Department department = departmentRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlug(universitySlug, facultySlug, departmentSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", departmentSlug));

        return updateDepartmentInternal(department, request);
    }

    /**
     * Xóa bộ môn bằng chuỗi slug (university -> faculty -> department)
     */
    public void deleteDepartmentByUniversityFacultyAndDepartmentSlug(String universitySlug,
                                                                     String facultySlug,
                                                                     String departmentSlug) {
        log.info("Deleting department with university slug: {}, faculty slug: {}, department slug: {}",
                universitySlug, facultySlug, departmentSlug);

        Department department = departmentRepository
                .findByUniversitySlugAndFacultySlugAndDepartmentSlug(universitySlug, facultySlug, departmentSlug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", departmentSlug));

        departmentRepository.delete(department);
        log.info("Department deleted successfully with slug: {}", departmentSlug);
    }

    /**
     * Internal method để xử lý logic update chung cho Department
     */
    private DepartmentResponse updateDepartmentInternal(Department department, DepartmentUpdateRequest request) {
        if (request.getName() != null && !request.getName().equals(department.getName())) {
            String newSlug = StringUtil.toSlug(request.getName());
            Long facultyId = department.getFaculty().getId();

            if (departmentRepository.existsByFacultyIdAndSlug(facultyId, newSlug)) {
                throw new DuplicateResourceException("Slug: " + newSlug + " đã tồn tại");
            }

            department.setSlug(newSlug);
        }

        departmentMapper.updateEntityFromRequest(request, department);

        Department updatedDepartment = departmentRepository.save(department);
        log.info("Department updated successfully with id: {}", updatedDepartment.getId());

        return departmentMapper.toResponse(updatedDepartment);
    }
}

