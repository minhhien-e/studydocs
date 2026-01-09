package com.example.academicservice.repository;

import com.example.academicservice.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository để truy vấn Major entity
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, UUID>, JpaSpecificationExecutor<Major> {

    // === ID-based queries ===

    // Tìm ngành theo slug trong 1 bộ môn
    Optional<Major> findByDepartmentIdAndSlug(UUID departmentId, String slug);

    // Kiểm tra slug đã tồn tại trong bộ môn chưa
    boolean existsByDepartmentIdAndSlug(UUID departmentId, String slug);
}

