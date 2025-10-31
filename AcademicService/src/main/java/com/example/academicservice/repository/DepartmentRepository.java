package com.example.academicservice.repository;

import com.example.academicservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository để truy vấn Department entity
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // === ID-based queries ===

    // Tìm tất cả các bộ môn thuộc 1 khoa
    List<Department> findByFacultyId(Long facultyId);

    // Tìm các bộ môn đang active thuộc 1 khoa
    List<Department> findByFacultyIdAndIsActive(Long facultyId, Boolean isActive);

    // Tìm bộ môn theo slug trong 1 khoa
    Optional<Department> findByFacultyIdAndSlug(Long facultyId, String slug);

    // Kiểm tra slug đã tồn tại trong khoa chưa
    boolean existsByFacultyIdAndSlug(Long facultyId, String slug);

    // === Slug-based queries ===

    // Tìm department theo university slug + faculty slug + department slug
    @Query("SELECT d FROM Department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug AND d.slug = :departmentSlug")
    Optional<Department> findByUniversitySlugAndFacultySlugAndDepartmentSlug(@Param("universitySlug") String universitySlug,
                                                                             @Param("facultySlug") String facultySlug,
                                                                             @Param("departmentSlug") String departmentSlug);

    // Lấy tất cả departments theo university slug + faculty slug
    @Query("SELECT d FROM Department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug")
    List<Department> findByUniversitySlugAndFacultySlug(@Param("universitySlug") String universitySlug,
                                                        @Param("facultySlug") String facultySlug);

    // Lấy departments active theo university slug + faculty slug
    @Query("SELECT d FROM Department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug AND d.isActive = :isActive")
    List<Department> findByUniversitySlugAndFacultySlugAndIsActive(@Param("universitySlug") String universitySlug,
                                                                   @Param("facultySlug") String facultySlug,
                                                                   @Param("isActive") Boolean isActive);

    // Kiểm tra department slug đã tồn tại trong faculty slug (kèm university slug) chưa
    @Query("SELECT COUNT(d) > 0 FROM Department d " +
            "JOIN d.faculty f " +
            "JOIN f.university u " +
            "WHERE u.slug = :universitySlug AND f.slug = :facultySlug AND d.slug = :departmentSlug")
    boolean existsByUniversitySlugAndFacultySlugAndDepartmentSlug(@Param("universitySlug") String universitySlug,
                                                                  @Param("facultySlug") String facultySlug,
                                                                  @Param("departmentSlug") String departmentSlug);
}

