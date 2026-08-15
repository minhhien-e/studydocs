package com.studydocs.modules.academic.repository;

import com.studydocs.modules.academic.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    List<SubjectEntity> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
    List<SubjectEntity> findByUniversityId(Long universityId);
}
