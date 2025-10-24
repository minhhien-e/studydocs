package com.example.academicservice.service;

import com.example.academicservice.dto.request.UniversityCreateRequest;
import com.example.academicservice.dto.response.UniversityResponse;
import com.example.academicservice.entity.University;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UniversityService {


    public List<UniversityResponse> getAllUniversities() {
        return null;
    }

    public UniversityResponse getUniversityById(Long id) {
        return null;
    }

    public UniversityResponse getUniversityBySlug(String slug) {
        return null;
    }

    public void deleteUniversityById(Long id) {
    }

    public void deleteUniversityBySlug(String slug) {
    }

    public UniversityResponse updateUniversity(Long id, UniversityCreateRequest request) {
        return null;
    }

    public UniversityResponse createUniversity(UniversityCreateRequest request) {
        return null;
    }
}