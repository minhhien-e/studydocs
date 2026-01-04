package com.example.academicservice.repository;

import com.example.academicservice.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository để truy vấn Faculty entity
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>, JpaSpecificationExecutor<Faculty> {

    // === ID-based queries ===
    
    // Tìm khoa theo slug trong 1 trường đại học
    Optional<Faculty> findByUniversityIdAndSlug(Long universityId, String slug);

    // Kiểm tra slug đã tồn tại trong trường đại học chưa
    boolean existsByUniversityIdAndSlug(Long universityId, String slug);
}

