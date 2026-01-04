package com.example.academicservice.repository.specification;

import com.example.academicservice.entity.University;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Specification class để tạo các điều kiện truy vấn động cho University entity
 */
public class UniversitySpecifications {

    /**
     * Tạo Specification để filter universities với các điều kiện optional
     * 
     * @param id - ID trường đại học (optional)
     * @param slug - Slug trường đại học (optional)
     * @param isActive - Lọc theo trạng thái active (optional, null = lấy tất cả)
     * @return Specification cho truy vấn động
     */
    public static Specification<University> filterBy(Long id, String slug, Boolean isActive) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            // Filter theo id
            if (id != null) {
                predicates.add(cb.equal(root.get("id"), id));
            }
            
            // Filter theo slug
            if (slug != null) {
                predicates.add(cb.equal(root.get("slug"), slug));
            }
            
            // Filter theo isActive
            if (isActive != null) {
                predicates.add(cb.equal(root.get("isActive"), isActive));
            }
            
            // Kết hợp tất cả điều kiện bằng AND
            return predicates.isEmpty() 
                ? cb.conjunction() 
                : cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

