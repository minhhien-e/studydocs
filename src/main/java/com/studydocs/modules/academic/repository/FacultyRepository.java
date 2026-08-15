package com.studydocs.modules.academic.repository;

import com.studydocs.modules.academic.entity.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<FacultyEntity, Long> {
    List<FacultyEntity> findByUniversityId(Long universityId);
    List<FacultyEntity> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
