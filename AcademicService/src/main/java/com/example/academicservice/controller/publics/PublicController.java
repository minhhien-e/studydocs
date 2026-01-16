package com.example.academicservice.controller.publics;

import com.example.academicservice.dto.response.SubjectResponse;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.service.SubjectService;
import com.example.academicservice.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
