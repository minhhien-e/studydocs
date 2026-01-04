package com.example.academicservice.service;

import com.example.academicservice.dto.request.FacultyCreateRequest;
import com.example.academicservice.dto.request.FacultyUpdateRequest;
import com.example.academicservice.dto.response.FacultyResponse;
import com.example.academicservice.entity.Faculty;
import com.example.academicservice.entity.University;
import com.example.academicservice.exception.DuplicateResourceException;
import com.example.academicservice.exception.ResourceNotFoundException;
import com.example.academicservice.mapper.FacultyMapper;
import com.example.academicservice.repository.FacultyRepository;
import com.example.academicservice.repository.UniversityRepository;
import com.example.academicservice.repository.specification.FacultySpecifications;
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer để xử lý business logic cho Faculty entity
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;
    private final FacultyMapper facultyMapper;

    /**
     * Lấy thông tin khoa theo ID
     */
    @Transactional(readOnly = true)
    public FacultyResponse getFacultyById(Long id) {
        log.info("Fetching faculty with id: {}", id);
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", id));
        return facultyMapper.toResponse(faculty);
    }

    /**
     * Tạo mới khoa
     */
    public FacultyResponse createFaculty(FacultyCreateRequest request) {
        log.info("Creating faculty with name: {} for university id: {}", request.getName(), request.getUniversityId());

        // Kiểm tra university có tồn tại không
        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("University", "id", request.getUniversityId()));

        // Kiểm tra slug đã tồn tại trong trường đại học chưa
        String slug = StringUtil.toSlug(request.getName());
        if (facultyRepository.existsByUniversityIdAndSlug(request.getUniversityId(), slug)) {
            throw new DuplicateResourceException("Khoa với slug: " + slug + " đã tồn tại trong trường này");
        }

        // Convert request sang entity
        Faculty faculty = facultyMapper.toEntity(request);
        faculty.setSlug(slug);
        faculty.setUniversity(university);
        faculty.setIsActive(true);

        // Lưu vào database
        Faculty savedFaculty = facultyRepository.save(faculty);
        log.info("Faculty created successfully with id: {}", savedFaculty.getId());

        return facultyMapper.toResponse(savedFaculty);
    }

    /**
     * Cập nhật thông tin khoa theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    public FacultyResponse updateFaculty(Long id, Long universityId, FacultyUpdateRequest request) {
        log.info("Updating faculty with id: {} and universityId: {}", id, universityId);

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên khoa
        if (!faculty.getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Faculty", "id", id + " không thuộc university " + universityId);
        }

        return updateFacultyInternal(faculty, request);
    }

    /**
     * Xóa khoa theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    public void deleteFacultyById(Long id, Long universityId) {
        log.info("Deleting faculty with id: {} and universityId: {}", id, universityId);

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên khoa
        if (!faculty.getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Faculty", "id", id + " không thuộc university " + universityId);
        }

        facultyRepository.delete(faculty);
        log.info("Faculty deleted successfully with id: {}", id);
    }

    /**
     * Cập nhật thông tin khoa theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    public FacultyResponse updateFacultyBySlug(Long universityId, String slug, FacultyUpdateRequest request) {
        log.info("Updating faculty with slug: {} and universityId: {}", slug, universityId);

        Faculty faculty = facultyRepository.findByUniversityIdAndSlug(universityId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên khoa
        if (!faculty.getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Faculty", "slug", slug + " không thuộc university " + universityId);
        }

        return updateFacultyInternal(faculty, request);
    }

    /**
     * Xóa khoa theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa
     */
    public void deleteFacultyBySlug(Long universityId, String slug) {
        log.info("Deleting faculty with slug: {} and universityId: {}", slug, universityId);

        Faculty faculty = facultyRepository.findByUniversityIdAndSlug(universityId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên khoa
        if (!faculty.getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Faculty", "slug", slug + " không thuộc university " + universityId);
        }

        facultyRepository.delete(faculty);
        log.info("Faculty deleted successfully with slug: {}", slug);
    }

    /**
     * Internal method để xử lý logic update chung
     */
    private FacultyResponse updateFacultyInternal(Faculty faculty, FacultyUpdateRequest request) {
        // Kiểm tra slug mới (nếu có thay đổi tên)
        if (request.getName() != null && !request.getName().equals(faculty.getName())) {
            String newSlug = StringUtil.toSlug(request.getName());
            Long universityId = faculty.getUniversity().getId();
            
            // Kiểm tra slug đã tồn tại trong university chưa
            if (facultyRepository.existsByUniversityIdAndSlug(universityId, newSlug)) {
                throw new DuplicateResourceException("Slug: " + newSlug + " đã tồn tại");
            }
            faculty.setSlug(newSlug);
        }

        // Cập nhật thông tin từ request
        facultyMapper.updateEntityFromRequest(request, faculty);

        // Lưu thay đổi
        Faculty updatedFaculty = facultyRepository.save(faculty);
        log.info("Faculty updated successfully with id: {}", updatedFaculty.getId());

        return facultyMapper.toResponse(updatedFaculty);
    }


    /**
     * Filter faculties với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách faculties
     */
    @Transactional(readOnly = true)
    public List<FacultyResponse> filter(Long universityId, String universitySlug, Boolean isActive) {
        log.info("Filtering faculties with universityId: {}, universitySlug: {}, isActive: {}", 
                universityId, universitySlug, isActive);
        
        Specification<Faculty> spec = FacultySpecifications.filterBy(universityId, universitySlug, isActive);
        List<Faculty> faculties = facultyRepository.findAll(spec);
        
        return faculties.stream()
                .map(facultyMapper::toResponse)
                .collect(Collectors.toList());
    }
}

