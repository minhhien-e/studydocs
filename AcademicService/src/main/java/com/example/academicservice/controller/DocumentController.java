package com.example.academicservice.controller;

import com.example.academicservice.dto.request.SubjectDocumentCreateRequest;
import com.example.academicservice.dto.response.DocumentRelationResponse;
import com.example.academicservice.dto.response.DocumentResponse;
import com.example.academicservice.entity.University;
import com.example.academicservice.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.util.UUID;

/**
 * Controller tập trung để truy xuất Documents.
 * Hỗ trợ filter theo nhiều cấp độ: University -> Faculty -> Department -> Subject.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics/documents")
public class DocumentController {

    // [FIX] Thêm 'final' để Lombok tạo constructor inject bean
    private final SubjectService subjectService;

    /**
     * API Lấy danh sách Documents.
     * Logic: Truyền vào parameter cấp nào sẽ lấy docs của cấp đó.
     * Ưu tiên: Subject > Department > Faculty > University.
     */
    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public DocumentResponse getDocuments(
            @RequestParam(required = false) UUID universityId,
            @RequestParam(required = false) UUID facultyId,
            @RequestParam(required = false) UUID departmentId,
            @RequestParam(required = false) UUID subjectId) {

        return subjectService.filterDocuments(universityId, facultyId, departmentId, subjectId);
    }

    /**
     * API Thêm Document vào Subject.
     * Validate chặt chẽ: Subject phải thuộc University truyền vào.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // [SEC] Yêu cầu Scope WRITE và Role ADMIN
   @PreAuthorize("hasAuthority('SCOPE_WRITE_USER')")
    public DocumentResponse addDocument(@Valid @RequestBody SubjectDocumentCreateRequest request) {
        return subjectService.addDocumentToSubject(request);
    }

    /*
     * Api trả về id của truing đại học và môn học dựa trên documentId
     * */
    @GetMapping("/info-by-document")
    @PreAuthorize("hasAuthority('SCOPE_READ_USER')")
    public DocumentRelationResponse getUniversityAndSubjectByDocumentId(
            @RequestParam UUID documentId) {
        return subjectService.getUniversityAndSubjectByDocumentId(documentId);
    }
}
