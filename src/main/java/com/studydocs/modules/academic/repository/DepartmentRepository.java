package com.studydocs.modules.academic.repository;

import com.studydocs.modules.academic.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    List<DepartmentEntity> findByFacultyId(Long facultyId);
    List<DepartmentEntity> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
