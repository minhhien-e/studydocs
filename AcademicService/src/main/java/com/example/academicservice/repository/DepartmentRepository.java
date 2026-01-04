package com.example.academicservice.repository;

import com.example.academicservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository để truy vấn Department entity
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {

    // === ID-based queries ===

    // Tìm bộ môn theo slug trong 1 khoa
    Optional<Department> findByFacultyIdAndSlug(Long facultyId, String slug);

    // Kiểm tra slug đã tồn tại trong khoa chưa
    boolean existsByFacultyIdAndSlug(Long facultyId, String slug);

}

