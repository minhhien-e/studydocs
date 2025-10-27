package com.example.academicservice.repository;

import com.example.academicservice.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository để truy vấn Faculty entity
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // === ID-based queries (existing) ===
    
    // Tìm tất cả các khoa thuộc 1 trường đại học
    List<Faculty> findByUniversityId(Long universityId);

    // Tìm các khoa đang active thuộc 1 trường đại học
    List<Faculty> findByUniversityIdAndIsActive(Long universityId, Boolean isActive);

    // Tìm khoa theo slug trong 1 trường đại học
    Optional<Faculty> findByUniversityIdAndSlug(Long universityId, String slug);

    // Kiểm tra slug đã tồn tại trong trường đại học chưa
    boolean existsByUniversityIdAndSlug(Long universityId, String slug);

    // === Slug-based queries (new) ===
    
    // Tìm faculty theo university slug + faculty slug
    @Query("SELECT f FROM Faculty f JOIN f.university u WHERE u.slug = :universitySlug AND f.slug = :facultySlug")
    Optional<Faculty> findByUniversitySlugAndFacultySlug(@Param("universitySlug") String universitySlug, 
                                                       @Param("facultySlug") String facultySlug);

    // Lấy tất cả faculties theo university slug
    @Query("SELECT f FROM Faculty f JOIN f.university u WHERE u.slug = :universitySlug")
    List<Faculty> findByUniversitySlug(@Param("universitySlug") String universitySlug);

    // Lấy faculties active theo university slug
    @Query("SELECT f FROM Faculty f JOIN f.university u WHERE u.slug = :universitySlug AND f.isActive = :isActive")
    List<Faculty> findByUniversitySlugAndIsActive(@Param("universitySlug") String universitySlug, 
                                                 @Param("isActive") Boolean isActive);

    // Kiểm tra faculty slug đã tồn tại trong university slug chưa
    @Query("SELECT COUNT(f) > 0 FROM Faculty f JOIN f.university u WHERE u.slug = :universitySlug AND f.slug = :facultySlug")
    boolean existsByUniversitySlugAndFacultySlug(@Param("universitySlug") String universitySlug, 
                                               @Param("facultySlug") String facultySlug);
}

