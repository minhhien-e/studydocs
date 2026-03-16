package com.example.academicservice.repository;

import com.example.academicservice.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UniversityRepository extends JpaRepository<University, UUID>, JpaSpecificationExecutor<University> {

    //Tìm kiếm theo slug
    Optional<University> findBySlug(String slug);
}
