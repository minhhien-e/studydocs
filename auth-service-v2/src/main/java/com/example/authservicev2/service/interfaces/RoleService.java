package com.example.authservicev2.service.interfaces;

import com.example.authservicev2.domain.model.entities.Permissions;
import com.example.authservicev2.domain.model.entities.Role;
import com.example.authservicev2.domain.model.request.AddRoleRequest;
import com.example.authservicev2.domain.model.request.DeleteRoleRequest;
import com.example.authservicev2.domain.model.response.RolePermissionResponse;

import java.util.List;

public interface RoleService {
    List<Permissions> getPermissionsByRoleId(Long roleId);
    RolePermissionResponse getPermissionsByRoleName(String roleName);
    Role findRoleById(Long roleId);
    Role findRoleByName(String roleName);
    void addRole(AddRoleRequest request);
    void deleteRole(DeleteRoleRequest request);
}