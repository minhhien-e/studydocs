package com.example.academicservice.repository;

import com.example.academicservice.entity.SubjectDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository để truy vấn SubjectDocument entity
 */
@Repository
public interface SubjectDocumentRepository extends JpaRepository<SubjectDocument, UUID> {

    List<SubjectDocument> findAllBySubject_Id(UUID subjectId);

    Optional<SubjectDocument> findByDocumentId(String documentId);

    boolean existsBySubjectIdAndDocumentId(UUID subjectId, String documentId);

    // --- Optimized JPQL Projections (Avoid N+1) ---

    @Query("SELECT sd.documentId FROM SubjectDocument sd WHERE sd.subject.id = :subjectId")
    List<String> findAllDocumentIdsBySubjectId(@Param("subjectId") UUID subjectId);

    @Query("SELECT sd.documentId FROM SubjectDocument sd WHERE sd.subject.department.id = :departmentId")
    List<String> findAllDocumentIdsByDepartmentId(@Param("departmentId") UUID departmentId);

    @Query("SELECT sd.documentId FROM SubjectDocument sd WHERE sd.subject.department.faculty.id = :facultyId")
    List<String> findAllDocumentIdsByFacultyId(@Param("facultyId") UUID facultyId);

    @Query("SELECT sd.documentId FROM SubjectDocument sd WHERE sd.subject.department.faculty.university.id = :universityId")
    List<String> findAllDocumentIdsByUniversityId(@Param("universityId") UUID universityId);
}
