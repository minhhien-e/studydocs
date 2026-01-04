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
import com.example.academicservice.repository.specification.MajorSpecifications;
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
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
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public MajorResponse updateMajor(Long id, Long universityId, MajorUpdateRequest request) {
        log.info("Updating major with id: {} and universityId: {}", id, universityId);

        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!major.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Major", "id", id + " không thuộc university " + universityId);
        }

        return updateMajorInternal(major, request);
    }

    /**
     * Xóa ngành theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public void deleteMajorById(Long id, Long universityId) {
        log.info("Deleting major with id: {} and universityId: {}", id, universityId);

        Major major = majorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!major.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Major", "id", id + " không thuộc university " + universityId);
        }

        majorRepository.delete(major);
        log.info("Major deleted successfully with id: {}", id);
    }

    /**
     * Cập nhật thông tin ngành theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public MajorResponse updateMajorBySlug(Long universityId, Long departmentId, String slug, MajorUpdateRequest request) {
        log.info("Updating major with slug: {} in department: {} and universityId: {}", slug, departmentId, universityId);

        Major major = majorRepository.findByDepartmentIdAndSlug(departmentId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!major.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Major", "slug", slug + " không thuộc university " + universityId);
        }

        return updateMajorInternal(major, request);
    }

    /**
     * Xóa ngành theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public void deleteMajorBySlug(Long universityId, Long departmentId, String slug) {
        log.info("Deleting major with slug: {} in department: {} and universityId: {}", slug, departmentId, universityId);

        Major major = majorRepository.findByDepartmentIdAndSlug(departmentId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Major", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!major.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Major", "slug", slug + " không thuộc university " + universityId);
        }

        majorRepository.delete(major);
        log.info("Major deleted successfully with slug: {}", slug);
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


    /**
     * Filter majors với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param facultyId - ID khoa (optional)
     * @param facultySlug - Slug khoa (optional)
     * @param departmentId - ID bộ môn (optional)
     * @param departmentSlug - Slug bộ môn (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách majors
     */
    @Transactional(readOnly = true)
    public List<MajorResponse> filter(Long universityId, String universitySlug,
                                     Long facultyId, String facultySlug,
                                     Long departmentId, String departmentSlug,
                                     Boolean isActive) {
        log.info("Filtering majors with universityId: {}, universitySlug: {}, " +
                "facultyId: {}, facultySlug: {}, departmentId: {}, departmentSlug: {}, isActive: {}",
                universityId, universitySlug, facultyId, facultySlug, departmentId, departmentSlug, isActive);
        
        Specification<Major> spec = MajorSpecifications.filterBy(
            universityId, universitySlug, facultyId, facultySlug, departmentId, departmentSlug, isActive
        );
        List<Major> majors = majorRepository.findAll(spec);
        
        return majors.stream()
                .map(majorMapper::toResponse)
                .collect(Collectors.toList());
    }
}

