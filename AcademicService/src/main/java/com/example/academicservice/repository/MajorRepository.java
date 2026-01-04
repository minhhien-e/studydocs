package com.example.academicservice.repository;

import com.example.academicservice.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository để truy vấn Major entity
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, Long>, JpaSpecificationExecutor<Major> {

    // === ID-based queries ===

    // Tìm ngành theo slug trong 1 bộ môn
    Optional<Major> findByDepartmentIdAndSlug(Long departmentId, String slug);

    // Kiểm tra slug đã tồn tại trong bộ môn chưa
    boolean existsByDepartmentIdAndSlug(Long departmentId, String slug);
}

