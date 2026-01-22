package com.example.academicservice.controller.publics;

import com.example.academicservice.dto.response.DocumentRelationResponse;
import com.example.academicservice.dto.response.SubjectResponse;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.service.SubjectService;
import com.example.academicservice.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/academics/public")
public class PublicController {
    private final SubjectService subjectService;
    private final UniversityService universityService;

    /**
     * Lấy thông tin môn học theo ID
     */
    @GetMapping("/subjects/id/{id}")
    public SubjectResponse getSubjectById(@PathVariable UUID id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping("/universities/id/{id}")
    public UniversityResponse getUniversityById(@PathVariable UUID id) {
        return universityService.getUniversityById(id);
    }
    /*
     * Api trả về id của truing đại học và môn học dựa trên documentId
     * */
    @GetMapping("/info-by-document")
    public DocumentRelationResponse getUniversityAndSubjectByDocumentId(
            @RequestParam UUID documentId) {
        return subjectService.getUniversityAndSubjectByDocumentId(documentId);
    }
}
