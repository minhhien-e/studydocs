package com.example.academicservice.service;

import com.example.academicservice.dto.request.SubjectCreateRequest;
import com.example.academicservice.dto.request.SubjectUpdateRequest;
import com.example.academicservice.dto.response.DocumentRelationResponse;
import com.example.academicservice.dto.response.DocumentResponse;
import com.example.academicservice.dto.response.SubjectResponse;
import com.example.academicservice.entity.Department;
import com.example.academicservice.entity.Subject;
import com.example.academicservice.entity.SubjectDocument;
import com.example.academicservice.exception.DuplicateResourceException;
import com.example.academicservice.exception.ResourceNotFoundException;
import com.example.academicservice.mapper.SubjectMapper;
import com.example.academicservice.repository.DepartmentRepository;
import com.example.academicservice.repository.SubjectDocumentRepository;
import com.example.academicservice.repository.SubjectRepository;
import com.example.academicservice.repository.specification.SubjectSpecifications;
import com.example.academicservice.dto.request.SubjectDocumentCreateRequest;
import com.example.academicservice.service.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.list;

/**
 * Service layer để xử lý business logic cho Subject entity
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectMapper subjectMapper;
    private final SubjectDocumentRepository subjectDocumentRepository;

    /**
     * Lấy thông tin môn học theo ID
     */
    @Transactional(readOnly = true)
    public SubjectResponse getSubjectById(UUID id) {
        log.info("Fetching subject with id: {}", id);
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        return subjectMapper.toResponse(subject);
    }

    /**
     * Tạo mới môn học
     */
    public SubjectResponse createSubject(SubjectCreateRequest request) {
        log.info("Creating subject with name: {} for department id: {}", request.getName(), request.getDepartmentId());

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", request.getDepartmentId()));

        String slug = StringUtil.toSlug(request.getName());
        if (subjectRepository.existsByDepartmentIdAndSlug(department.getId(), slug)) {
            throw new DuplicateResourceException("Môn học với slug: " + slug + " đã tồn tại trong bộ môn này");
        }

        Subject subject = subjectMapper.toEntity(request);
        subject.setSlug(slug);
        subject.setDepartment(department);
        subject.setIsActive(true);

        Subject savedSubject = subjectRepository.save(subject);
        log.info("Subject created successfully with id: {}", savedSubject.getId());

        return subjectMapper.toResponse(savedSubject);
    }

    /**
     * Cập nhật thông tin môn học theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public SubjectResponse updateSubject(UUID id, UUID universityId, SubjectUpdateRequest request) {
        log.info("Updating subject with id: {} and universityId: {}", id, universityId);

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!subject.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Subject", "id", id + " không thuộc university " + universityId);
        }

        return updateSubjectInternal(subject, request);
    }

    /**
     * Xóa môn học theo ID
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public void deleteSubjectById(UUID id, UUID universityId) {
        log.info("Deleting subject with id: {} and universityId: {}", id, universityId);

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!subject.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Subject", "id", id + " không thuộc university " + universityId);
        }

        subjectRepository.delete(subject);
        log.info("Subject deleted successfully with id: {}", id);
    }

    /**
     * Cập nhật thông tin môn học theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public SubjectResponse updateSubjectBySlug(UUID universityId, UUID departmentId, String slug, SubjectUpdateRequest request) {
        log.info("Updating subject with slug: {} in department: {} and universityId: {}", slug, departmentId, universityId);

        Subject subject = subjectRepository.findByDepartmentIdAndSlug(departmentId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!subject.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Subject", "slug", slug + " không thuộc university " + universityId);
        }

        return updateSubjectInternal(subject, request);
    }

    /**
     * Xóa môn học theo slug
     * Bắt buộc phải có universityId để validate tránh conflict khi 2 trường có cùng tên khoa/ngành/môn
     */
    public void deleteSubjectBySlug(UUID universityId, UUID departmentId, String slug) {
        log.info("Deleting subject with slug: {} in department: {} and universityId: {}", slug, departmentId, universityId);

        Subject subject = subjectRepository.findByDepartmentIdAndSlug(departmentId, slug)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "slug", slug));

        // Validate universityId để đảm bảo không nhầm lẫn khi 2 trường có cùng tên
        if (!subject.getDepartment().getFaculty().getUniversity().getId().equals(universityId)) {
            throw new ResourceNotFoundException("Subject", "slug", slug + " không thuộc university " + universityId);
        }

        subjectRepository.delete(subject);
        log.info("Subject deleted successfully with slug: {}", slug);
    }

    /**
     * Internal method để xử lý logic update chung cho Subject
     */
    private SubjectResponse updateSubjectInternal(Subject subject, SubjectUpdateRequest request) {
        if (request.getName() != null && !request.getName().equals(subject.getName())) {
            String newSlug = StringUtil.toSlug(request.getName());
            UUID departmentId = subject.getDepartment().getId();

            // Nếu slug mới giống slug hiện tại (khác dấu/case...), bỏ qua check trùng để tránh false-positive
            if (!newSlug.equals(subject.getSlug())) {
                if (subjectRepository.existsByDepartmentIdAndSlug(departmentId, newSlug)) {
                    throw new DuplicateResourceException("Slug: " + newSlug + " đã tồn tại");
                }
                subject.setSlug(newSlug);
            }
        }

        subjectMapper.updateEntityFromRequest(request, subject);

        Subject updatedSubject = subjectRepository.save(subject);
        log.info("Subject updated successfully with id: {}", updatedSubject.getId());

        return subjectMapper.toResponse(updatedSubject);
    }


    /**
     * Filter subjects với query parameters
     * Tất cả các tham số đều optional, có thể kết hợp nhiều filter cùng lúc
     *
     * @param universityId   - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param facultyId      - ID khoa (optional)
     * @param facultySlug    - Slug khoa (optional)
     * @param departmentId   - ID bộ môn (optional)
     * @param departmentSlug - Slug bộ môn (optional)
     * @param isActive       - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Danh sách môn học
     */
    @Transactional(readOnly = true)
    public List<SubjectResponse> filter(UUID universityId, String universitySlug,
                                        UUID facultyId, String facultySlug,
                                        UUID departmentId, String departmentSlug,
                                        Boolean isActive) {
        log.info("Filtering subjects with universityId: {}, universitySlug: {}, " +
                        "facultyId: {}, facultySlug: {}, departmentId: {}, departmentSlug: {}, isActive: {}",
                universityId, universitySlug, facultyId, facultySlug, departmentId, departmentSlug, isActive);

        Specification<Subject> spec = SubjectSpecifications.filterBy(
                universityId, universitySlug, facultyId, facultySlug, departmentId, departmentSlug, isActive
        );
        List<Subject> subjects = subjectRepository.findAll(spec);

        return subjects.stream()
                .map(subjectMapper::toResponse)
                .collect(Collectors.toList());
    }

    //    @Transactional(readOnly = true)
    public DocumentResponse getSubjectDocuments(UUID subjectId) {
        // [FIX] Use filter logic or kept simple but safe
        List<SubjectDocument> subjectDocuments = subjectDocumentRepository.findAllBySubject_Id(subjectId);

        if (subjectDocuments.isEmpty()) {
            // [FIX] Return empty list instead of 404
            return new DocumentResponse(List.of()
            );
        }

        List<UUID> documentIds = subjectDocuments.stream()
                .map(SubjectDocument::getDocumentId)
                .map(UUID::fromString)
                .collect(java.util.stream.Collectors.toList());

        DocumentResponse response = new DocumentResponse();
        response.setDocumentIds(documentIds);

        return response;
    }

    /**
     * Lấy danh sách document IDs theo cấp độ (Subject -> Department -> Faculty -> University)
     * Ưu tiên ID cấp thấp nhất (cụ thể nhất).
     */
    @Transactional(readOnly = true)
    public DocumentResponse filterDocuments(UUID universityId, UUID facultyId, UUID departmentId, UUID subjectId) {
        log.info("Filtering documents - uni: {}, fac: {}, dept: {}, sub: {}", universityId, facultyId, departmentId, subjectId);

        List<String> rawIds;

        if (subjectId != null) {
            rawIds = subjectDocumentRepository.findAllDocumentIdsBySubjectId(subjectId);
        } else if (departmentId != null) {
            rawIds = subjectDocumentRepository.findAllDocumentIdsByDepartmentId(departmentId);
        } else if (facultyId != null) {
            rawIds = subjectDocumentRepository.findAllDocumentIdsByFacultyId(facultyId);
        } else if (universityId != null) {
            rawIds = subjectDocumentRepository.findAllDocumentIdsByUniversityId(universityId);
        } else {
            return new DocumentResponse(List.of());
        }

        List<UUID> uuids = rawIds.stream()
                .map(idStr -> {
                    try {
                        return UUID.fromString(idStr);
                    } catch (IllegalArgumentException e) {
                        log.error("Invalid UUID in database: {}", idStr);
                        return null;
                    }
                })
                .filter(java.util.Objects::nonNull)
                .collect(java.util.stream.Collectors.toList());

        DocumentResponse response = new DocumentResponse();
        response.setDocumentIds(uuids);
        return response;
    }

    /**
     * Thêm document vào môn học
     * Validate:
     * 1. Subject thuộc University (tránh ID spoofing)
     * 2. DocumentId chưa tồn tại trong Subject này
     */
    public DocumentResponse addDocumentToSubject(SubjectDocumentCreateRequest request) {
        log.info("Adding document {} to subject {} (uni: {})", request.getDocumentId(), request.getSubjectId(), request.getUniversityId());

        // 1. Fetch Subject
        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", request.getSubjectId()));

        // 2. Validate University Hierarchy
        UUID actualUniId = subject.getDepartment().getFaculty().getUniversity().getId();
        if (!actualUniId.equals(request.getUniversityId())) {
            throw new ResourceNotFoundException("Subject", "id", request.getSubjectId() + " không thuộc University: " + request.getUniversityId());
        }

        // 3. Validate Duplicate Document
        if (subjectDocumentRepository.existsBySubjectIdAndDocumentId(request.getSubjectId(), request.getDocumentId().toString())) {
            throw new DuplicateResourceException("Document " + request.getDocumentId() + " đã tồn tại trong môn học này");
        }

        // 4. Save
        SubjectDocument subjectDocument = new SubjectDocument();
        subjectDocument.setSubject(subject);
        subjectDocument.setDocumentId(request.getDocumentId().toString());
        // Description removed per requirements
        subjectDocument.setIsActive(true);

        subjectDocumentRepository.save(subjectDocument);
        log.info("Added document successfully");

        // Return updated list or just the single ID
        DocumentResponse response = new DocumentResponse();
        response.setDocumentIds(java.util.List.of(request.getDocumentId()));
        return response;
    }

    public DocumentRelationResponse getUniversityAndSubjectByDocumentId(UUID documentId) {
        log.info("Fetching University and Subject by Document ID: {}", documentId);

        SubjectDocument subjectDocument = subjectDocumentRepository.findByDocumentId(documentId.toString())
                .orElseThrow(() -> new ResourceNotFoundException("SubjectDocument", "documentId", documentId));

        Subject subject = subjectDocument.getSubject();
        UUID subjectId = subject.getId();
        UUID universityId = subject.getDepartment().getFaculty().getUniversity().getId();

        DocumentRelationResponse response = new DocumentRelationResponse();
        response.setUniversityId(universityId);
        response.setSubjectId(subjectId);

        return response;
    }
}
