package com.studydocs.modules.academic.repository;

import com.studydocs.modules.academic.entity.UniversityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<UniversityEntity, Long> {
    List<UniversityEntity> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
