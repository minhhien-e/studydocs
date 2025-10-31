package com.example.academicservice.repository;

import com.example.academicservice.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository để truy vấn Major entity
 */
@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {

    // === ID-based queries ===

    // Tìm tất cả các ngành thuộc 1 bộ môn
    List<Major> findByDepartmentId(Long departmentId);

    // Tìm các ngành đang active thuộc 1 bộ môn
    List<Major> findByDepartmentIdAndIsActive(Long departmentId, Boolean isActive);

    // Tìm ngành theo slug trong 1 bộ môn
    Optional<Major> findByDepartmentIdAndSlug(Long departmentId, String slug);

    // Kiểm tra slug đã tồn tại trong bộ môn chưa
    boolean existsByDepartmentIdAndSlug(Long departmentId, String slug);

    // === Slug-based queries ===

    // Tìm major theo chuỗi slug: university -> faculty -> department -> major
    @Query("SELECT m FROM Major m " +
            "JOIN m.department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug " +
            "AND d.slug = :departmentSlug AND m.slug = :majorSlug")
    Optional<Major> findByUniversitySlugAndFacultySlugAndDepartmentSlugAndMajorSlug(
            @Param("universitySlug") String universitySlug,
            @Param("facultySlug") String facultySlug,
            @Param("departmentSlug") String departmentSlug,
            @Param("majorSlug") String majorSlug);

    // Lấy tất cả majors theo chuỗi slug cha
    @Query("SELECT m FROM Major m " +
            "JOIN m.department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug " +
            "AND d.slug = :departmentSlug")
    List<Major> findByUniversitySlugAndFacultySlugAndDepartmentSlug(
            @Param("universitySlug") String universitySlug,
            @Param("facultySlug") String facultySlug,
            @Param("departmentSlug") String departmentSlug);

    // Lấy majors active theo chuỗi slug cha
    @Query("SELECT m FROM Major m " +
            "JOIN m.department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug " +
            "AND d.slug = :departmentSlug AND m.isActive = :isActive")
    List<Major> findByUniversitySlugAndFacultySlugAndDepartmentSlugAndIsActive(
            @Param("universitySlug") String universitySlug,
            @Param("facultySlug") String facultySlug,
            @Param("departmentSlug") String departmentSlug,
            @Param("isActive") Boolean isActive);

    // Kiểm tra major slug đã tồn tại theo chuỗi slug cha chưa
    @Query("SELECT COUNT(m) > 0 FROM Major m " +
            "JOIN m.department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug " +
            "AND d.slug = :departmentSlug AND m.slug = :majorSlug")
    boolean existsByUniversitySlugAndFacultySlugAndDepartmentSlugAndMajorSlug(
            @Param("universitySlug") String universitySlug,
            @Param("facultySlug") String facultySlug,
            @Param("departmentSlug") String departmentSlug,
            @Param("majorSlug") String majorSlug);
}

