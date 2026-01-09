package com.example.academicservice.repository.specification;

import com.example.academicservice.entity.Major;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Specification class để tạo các điều kiện truy vấn động cho Major entity
 */
public class MajorSpecifications {

    /**
     * Tạo Specification để filter majors với các điều kiện optional
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param facultyId - ID khoa (optional)
     * @param facultySlug - Slug khoa (optional)
     * @param departmentId - ID bộ môn (optional)
     * @param departmentSlug - Slug bộ môn (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Specification cho truy vấn động
     */
    public static Specification<Major> filterBy(
            UUID universityId, 
            String universitySlug,
            UUID facultyId, 
            String facultySlug,
            UUID departmentId,
            String departmentSlug,
            Boolean isActive) {
        
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // Filter theo universityId
            if (universityId != null) {
                predicates.add(
                    cb.equal(root.get("department").get("faculty").get("university").get("id"), universityId)
                );
            }
            
            // Filter theo universitySlug
            if (universitySlug != null) {
                predicates.add(
                    cb.equal(root.get("department").get("faculty").get("university").get("slug"), universitySlug)
                );
            }
            
            // Filter theo facultyId
            if (facultyId != null) {
                predicates.add(
                    cb.equal(root.get("department").get("faculty").get("id"), facultyId)
                );
            }
            
            // Filter theo facultySlug
            if (facultySlug != null) {
                predicates.add(
                    cb.equal(root.get("department").get("faculty").get("slug"), facultySlug)
                );
            }
            
            // Filter theo departmentId
            if (departmentId != null) {
                predicates.add(
                    cb.equal(root.get("department").get("id"), departmentId)
                );
            }
            
            // Filter theo departmentSlug
            if (departmentSlug != null) {
                predicates.add(
                    cb.equal(root.get("department").get("slug"), departmentSlug)
                );
            }
            
            // Filter theo isActive
            if (isActive != null) {
                predicates.add(
                    cb.equal(root.get("isActive"), isActive)
                );
            }
            
            // Kết hợp tất cả điều kiện bằng AND
            return predicates.isEmpty() 
                ? cb.conjunction() 
                : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

