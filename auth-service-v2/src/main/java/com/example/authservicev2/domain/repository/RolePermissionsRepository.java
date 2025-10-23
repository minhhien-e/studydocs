package com.example.authservicev2.domain.repository;

import com.example.authservicev2.domain.model.entities.Permissions;
import com.example.authservicev2.domain.model.entities.Role_permissions;
import com.example.authservicev2.domain.model.entities.Role_permissionsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionsRepository extends JpaRepository<Role_permissions, Role_permissionsId> {
    
    // Lấy tất cả Role_permissions của một role
    List<Role_permissions> findByRoleId(@Param("roleId") Long roleId);
    
    // Lấy permissions qua Role_permissions entity relationship từ một role
    @Query("SELECT rp.permission FROM Role_permissions rp WHERE rp.role.id = :roleId")
    List<Permissions> findPermissionsByRoleId(@Param("roleId") Long roleId);
    
    // Lấy permissions từ nhiều roles (cho user có nhiều roles)
    @Query("SELECT rp.permission FROM Role_permissions rp WHERE rp.role.id IN :roleIds")
    List<Permissions> findPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);
    
    // Kiểm tra role có permission cụ thể không
    boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
