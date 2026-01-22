package com.example.academicservice.service;

import com.example.academicservice.dto.request.UniversityCreateRequest;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.entity.University;
import com.example.academicservice.exception.DuplicateResourceException;
import com.example.academicservice.exception.ResourceNotFoundException;
import com.example.academicservice.mapper.UniversityMapper;
import com.example.academicservice.repository.UniversityRepository;
import com.example.academicservice.repository.specification.UniversitySpecifications;
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
 * Service layer để xử lý business logic cho University entity
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    /**
     * Lấy tất cả các trường đại học
     */
    @Transactional(readOnly = true)
    public List<UniversityResponse> getAllUniversities() {
        log.info("Fetching all universities");
        return universityRepository.findAll().stream()
                .map(universityMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Lấy thông tin trường đại học theo ID
     */
    @Transactional(readOnly = true)
    public UniversityResponse getUniversityById(UUID id) {
        log.info("Fetching university with id: {}", id);
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University", "id", id));
        return universityMapper.toResponse(university);
    }

    /**
     * Lấy thông tin trường đại học theo slug
     */
    @Transactional(readOnly = true)
    public UniversityResponse getUniversityBySlug(String slug) {
        log.info("Fetching university with slug: {}", slug);
        University university = universityRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("University", "slug", slug));
        return universityMapper.toResponse(university);
    }

    /**
     * Tạo mới trường đại học
     */
    public UniversityResponse createUniversity(UniversityCreateRequest request) {
        log.info("Creating university with name: {}", request.getName());

        // ✅ Sinh slug từ name
        String slug = StringUtil.toSlug(request.getName());

        // Check slug trùng
        if (universityRepository.findBySlug(slug).isPresent()) {
            throw new DuplicateResourceException("Trường đại học với slug: " + slug + " đã tồn tại");
        }

        // Check code trùng
        if (universityRepository.findByCode(request.getCode()).isPresent()) {
            throw new DuplicateResourceException("Trường đại học với code: " + request.getCode() + " đã tồn tại");
        }

        University university = universityMapper.toEntity(request);
        university.setSlug(slug);
        university.setIsActive(true);

        University saved = universityRepository.save(university);
        return universityMapper.toResponse(saved);
    }


    /**
     * Cập nhật thông tin trường đại học theo ID
     */
    public UniversityResponse updateUniversity(UUID id, UniversityCreateRequest request) {
        log.info("Updating university with id: {}", id);
        
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("University", "id", id));
        
        return updateUniversityInternal(university, request);
    }

    /**
     * Cập nhật thông tin trường đại học theo slug
     */
    public UniversityResponse updateUniversityBySlug(String slug, UniversityCreateRequest request) {
        log.info("Updating university with slug: {}", slug);
        
        University university = universityRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("University", "slug", slug));
        
        return updateUniversityInternal(university, request);
    }

    /**
     * Internal method để xử lý logic update chung
     */
    private UniversityResponse updateUniversityInternal(University university, UniversityCreateRequest request) {
        // Kiểm tra slug mới (nếu có thay đổi tên)
        if (request.getName() != null && !request.getName().equals(university.getName())) {
            String newSlug = StringUtil.toSlug(request.getName());
            // Nếu slug mới giống slug hiện tại (khác dấu/case...), bỏ qua check trùng để tránh false-positive
            if (!newSlug.equals(university.getSlug())) {
                universityRepository.findBySlug(newSlug).ifPresent(existing -> {
                    // Exclude chính record đang update
                    if (!existing.getId().equals(university.getId())) {
                        throw new DuplicateResourceException("Slug: " + newSlug + " đã tồn tại");
                    }
                });
                university.setSlug(newSlug);
            }
        }

        // Cập nhật thông tin từ request
        universityMapper.updateEntityFromRequest(request, university);

        // Lưu thay đổi
        University updatedUniversity = universityRepository.save(university);
        log.info("University updated successfully with id: {}", updatedUniversity.getId());

        return universityMapper.toResponse(updatedUniversity);
    }

    /**
     * Xóa trường đại học theo ID
     */
    public void deleteUniversityById(UUID id) {
        log.info("Deleting university with id: {}", id);

        if (!universityRepository.existsById(id)) {
            throw new ResourceNotFoundException("University", "id", id);
        }

        universityRepository.deleteById(id);
        log.info("University deleted successfully with id: {}", id);
    }

    /**
     * Xóa trường đại học theo slug
     */
    public void deleteUniversityBySlug(String slug) {
        log.info("Deleting university with slug: {}", slug);

        University university = universityRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("University", "slug", slug));

        universityRepository.delete(university);
        log.info("University deleted successfully with slug: {}", slug);
    }

    /**
     * Filter universities với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     * 
     * @param id - ID trường đại học (optional)
     * @param slug - Slug trường đại học (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách universities
     */
    @Transactional(readOnly = true)
    public List<UniversityResponse> filter(UUID id, String slug, Boolean isActive) {
        log.info("Filtering universities with id: {}, slug: {}, isActive: {}", id, slug, isActive);
        
        Specification<University> spec = UniversitySpecifications.filterBy(id, slug, isActive);
        List<University> universities = universityRepository.findAll(spec);
        
        return universities.stream()
                .map(universityMapper::toResponse)
                .collect(Collectors.toList());
    }
}