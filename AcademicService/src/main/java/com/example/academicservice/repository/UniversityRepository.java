package com.example.academicservice.repository;

import com.example.academicservice.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>, JpaSpecificationExecutor<University> {

    //Tìm kiếm theo slug
    Optional<University> findBySlug(String slug);
}
