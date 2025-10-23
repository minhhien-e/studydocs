package com.example.authservicev2.domain.repository;

import com.example.authservicev2.domain.model.entities.Role;
import com.example.authservicev2.domain.model.entities.User_roles;
import com.example.authservicev2.domain.model.entities.User_rolesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<User_roles, User_rolesId> {

    // Lấy tất cả User_roles của một user
    List<User_roles> findByUserId(@Param("userId") Long userId);

    // Lấy roles qua User_roles entity relationship
    @Query("SELECT ur.role FROM User_roles ur WHERE ur.user.id = :userId")
    List<Role> findRolesByUserId(@Param("userId") Long userId);

    // Kiểm tra user có role cụ thể không
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
}