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
import com.example.academicservice.repository.specification.DepartmentSpecifications;
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
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

    /**
     * Lấy thông tin bộ môn theo ID
     */
    @Transactional(readOnly = true)
    public DepartmentResponse getDepartmentById(UUID id) {
        log.info("Fetching department with id: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));
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
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public DepartmentResponse updateDepartment(UUID id, UUID universityId, DepartmentUpdateRequest request) {
        log.info("Updating department with id: {} and universityId: {}", id, universityId);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!department.getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Department", "id", id + " không thuộc university " + universityId);
        }

        return updateDepartmentInternal(department, request);
    }

    /**
     * Xóa bộ môn theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public void deleteDepartmentById(UUID id, UUID universityId) {
        log.info("Deleting department with id: {} and universityId: {}", id, universityId);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!department.getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Department", "id", id + " không thuộc university " + universityId);
        }

        departmentRepository.delete(department);
        log.info("Department deleted successfully with id: {}", id);
    }

    /**
     * Cập nhật thông tin bộ môn theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public DepartmentResponse updateDepartmentBySlug(UUID universityId, UUID facultyId, String slug, DepartmentUpdateRequest request) {
        log.info("Updating department with slug: {} in faculty: {} and universityId: {}", slug, facultyId, universityId);

        Department department = departmentRepository.findByFacultyIdAndSlug(facultyId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!department.getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Department", "slug", slug + " không thuộc university " + universityId);
        }

        return updateDepartmentInternal(department, request);
    }

    /**
     * Xóa bộ môn theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public void deleteDepartmentBySlug(UUID universityId, UUID facultyId, String slug) {
        log.info("Deleting department with slug: {} in faculty: {} and universityId: {}", slug, facultyId, universityId);

        Department department = departmentRepository.findByFacultyIdAndSlug(facultyId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!department.getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Department", "slug", slug + " không thuộc university " + universityId);
        }

        departmentRepository.delete(department);
        log.info("Department deleted successfully with slug: {}", slug);
    }

    /**
     * Internal method để xử lý logic update chung cho Department
     */
    private DepartmentResponse updateDepartmentInternal(Department department, DepartmentUpdateRequest request) {
        if (request.getName() != null && !request.getName().equals(department.getName())) {
            String newSlug = StringUtil.toSlug(request.getName());
            UUID facultyId = department.getFaculty().getId();

            // Nếu slug mới giống slug hiện tại (khác dấu/case...), bỏ qua check trùng để tránh false-positive
            if (!newSlug.equals(department.getSlug())) {
                if (departmentRepository.existsByFacultyIdAndSlug(facultyId, newSlug)) {
                    throw new DuplicateResourceException("Slug: " + newSlug + " đã tồn tại");
                }
                department.setSlug(newSlug);
            }
        }

        departmentMapper.updateEntityFromRequest(request, department);

        Department updatedDepartment = departmentRepository.save(department);
        log.info("Department updated successfully with id: {}", updatedDepartment.getId());

        return departmentMapper.toResponse(updatedDepartment);
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
     * @return Danh sách departments
     */
    @Transactional(readOnly = true)
    public List<DepartmentResponse> filter(UUID universityId, String universitySlug,
                                          UUID facultyId, String facultySlug,
                                          Boolean isActive) {
        log.info("Filtering departments with universityId: {}, universitySlug: {}, " +
                "facultyId: {}, facultySlug: {}, isActive: {}",
                universityId, universitySlug, facultyId, facultySlug, isActive);
        
        Specification<Department> spec = DepartmentSpecifications.filterBy(
            universityId, universitySlug, facultyId, facultySlug, isActive
        );
        List<Department> departments = departmentRepository.findAll(spec);
        
        return departments.stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }
}

