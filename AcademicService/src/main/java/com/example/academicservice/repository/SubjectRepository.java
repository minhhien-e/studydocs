package com.example.academicservice.repository;

import com.example.academicservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository để truy vấn Subject entity
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID>, JpaSpecificationExecutor<Subject> {

    // === ID-based queries ===

    // Tìm môn học theo slug trong 1 bộ môn
    Optional<Subject> findByDepartmentIdAndSlug(UUID departmentId, String slug);

    // Kiểm tra slug đã tồn tại trong bộ môn chưa
    boolean existsByDepartmentIdAndSlug(UUID departmentId, String slug);
}
