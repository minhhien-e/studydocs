package com.example.academicservice.repository.specification;

import com.example.academicservice.entity.Faculty;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification class để tạo các điều kiện truy vấn động cho Faculty entity
 */
public class FacultySpecifications {

    /**
     * Tạo Specification để filter faculties với các điều kiện optional
     * 
     * @param universityId - ID trường đại học (optional)
     * @param universitySlug - Slug trường đại học (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Specification cho truy vấn động
     */
    public static Specification<Faculty> filterBy(Long universityId, String universitySlug, Boolean isActive) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // Filter theo universityId
            if (universityId != null) {
                predicates.add(
                    cb.equal(root.get("university").get("id"), universityId)
                );
            }
            
            // Filter theo universitySlug
            if (universitySlug != null) {
                predicates.add(
                    cb.equal(root.get("university").get("slug"), universitySlug)
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

