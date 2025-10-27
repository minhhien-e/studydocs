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
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
     * Lấy tất cả các khoa theo ID trường đại học
     */
    @Transactional(readOnly = true)
    public List<FacultyResponse> getAllFacultiesByUniversityId(Long universityId) {
        log.info("Fetching all faculties for university id: {}", universityId);
        return facultyRepository.findByUniversityId(universityId).stream()
                .map(facultyMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy các khoa đang active theo ID trường đại học
     */
    @Transactional(readOnly = true)
    public List<FacultyResponse> getActiveFacultiesByUniversityId(Long universityId) {
        log.info("Fetching active faculties for university id: {}", universityId);
        return facultyRepository.findByUniversityIdAndIsActive(universityId, true).stream()
                .map(facultyMapper::toResponse)
                .collect(Collectors.toList());
    }

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
     * Lấy thông tin khoa theo slug trong một trường đại học
     */
    @Transactional(readOnly = true)
    public FacultyResponse getFacultyBySlug(Long universityId, String slug) {
        log.info("Fetching faculty with slug: {} in university: {}", slug, universityId);
        Faculty faculty = facultyRepository.findByUniversityIdAndSlug(universityId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", slug));
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
     */
    public FacultyResponse updateFaculty(Long id, FacultyUpdateRequest request) {
        log.info("Updating faculty with id: {}", id);

        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "id", id));

        return updateFacultyInternal(faculty, request);
    }

    /**
     * Xóa khoa theo ID
     */
    public void deleteFacultyById(Long id) {
        log.info("Deleting faculty with id: {}", id);

        if (!facultyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Faculty", "id", id);
        }

        facultyRepository.deleteById(id);
        log.info("Faculty deleted successfully with id: {}", id);
    }

    /**
     * Xóa khoa theo slug
     */
    public void deleteFacultyBySlug(Long universityId, String slug) {
        log.info("Deleting faculty with slug: {} in university: {}", slug, universityId);

        Faculty faculty = facultyRepository.findByUniversityIdAndSlug(universityId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", slug));

        facultyRepository.delete(faculty);
        log.info("Faculty deleted successfully with slug: {}", slug);
    }

    // === Slug-based methods (new) ===

    /**
     * Lấy tất cả các khoa theo university slug
     */
    @Transactional(readOnly = true)
    public List<FacultyResponse> getAllFacultiesByUniversitySlug(String universitySlug) {
        log.info("Fetching all faculties for university slug: {}", universitySlug);
        return facultyRepository.findByUniversitySlug(universitySlug).stream()
                .map(facultyMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy các khoa đang active theo university slug
     */
    @Transactional(readOnly = true)
    public List<FacultyResponse> getActiveFacultiesByUniversitySlug(String universitySlug) {
        log.info("Fetching active faculties for university slug: {}", universitySlug);
        return facultyRepository.findByUniversitySlugAndIsActive(universitySlug, true).stream()
                .map(facultyMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin khoa theo university slug + faculty slug
     */
    @Transactional(readOnly = true)
    public FacultyResponse getFacultyByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug) {
        log.info("Fetching faculty with university slug: {} and faculty slug: {}", universitySlug, facultySlug);
        Faculty faculty = facultyRepository.findByUniversitySlugAndFacultySlug(universitySlug, facultySlug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", facultySlug));
        return facultyMapper.toResponse(faculty);
    }

    /**
     * Tạo mới khoa bằng university slug
     */
    public FacultyResponse createFacultyByUniversitySlug(String universitySlug, FacultyCreateRequest request) {
        log.info("Creating faculty with name: {} for university slug: {}", request.getName(), universitySlug);

        // Tìm university theo slug
        University university = universityRepository.findBySlug(universitySlug)
                .orElseThrow(() -> new ResourceNotFoundException("University", "slug", universitySlug));

        // Kiểm tra slug đã tồn tại trong trường đại học chưa
        String slug = StringUtil.toSlug(request.getName());
        if (facultyRepository.existsByUniversitySlugAndFacultySlug(universitySlug, slug)) {
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
     * Cập nhật thông tin khoa bằng university slug + faculty slug
     */
    public FacultyResponse updateFacultyByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug, FacultyUpdateRequest request) {
        log.info("Updating faculty with university slug: {} and faculty slug: {}", universitySlug, facultySlug);

        Faculty faculty = facultyRepository.findByUniversitySlugAndFacultySlug(universitySlug, facultySlug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", facultySlug));

        return updateFacultyInternal(faculty, request);
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
     * Xóa khoa theo university slug + faculty slug
     */
    public void deleteFacultyByUniversitySlugAndFacultySlug(String universitySlug, String facultySlug) {
        log.info("Deleting faculty with university slug: {} and faculty slug: {}", universitySlug, facultySlug);

        Faculty faculty = facultyRepository.findByUniversitySlugAndFacultySlug(universitySlug, facultySlug)
                .orElseThrow(() -> new ResourceNotFoundException("Faculty", "slug", facultySlug));

        facultyRepository.delete(faculty);
        log.info("Faculty deleted successfully with slug: {}", facultySlug);
    }
}

