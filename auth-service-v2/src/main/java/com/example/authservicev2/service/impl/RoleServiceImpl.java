package com.example.authservicev2.service.impl;

import com.example.authservicev2.domain.model.entities.Permissions;
import com.example.authservicev2.domain.model.entities.Role;
import com.example.authservicev2.domain.model.entities.Role_permissions;
import com.example.authservicev2.domain.model.request.AddRoleRequest;
import com.example.authservicev2.domain.model.request.DeleteRoleRequest;
import com.example.authservicev2.domain.model.response.RolePermissionResponse;
import com.example.authservicev2.domain.repository.RolePermissionsRepository;
import com.example.authservicev2.domain.repository.RoleRepository;
import com.example.authservicev2.exception.CustomExceptions;
import com.example.authservicev2.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RolePermissionsRepository rolePermissionsRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<Permissions> getPermissionsByRoleId(Long roleId) {
        return rolePermissionsRepository.findPermissionsByRoleId(roleId);
    }

    @Override
    public RolePermissionResponse getPermissionsByRoleName(String roleName) {
        Role role = findRoleByName(roleName);
        List<Permissions> permissions = getPermissionsByRoleId(role.getId());
        if (permissions.isEmpty()) {
            throw new CustomExceptions.ValidationException("khong co quyen");    }
        RolePermissionResponse response = new RolePermissionResponse(role, permissions);
        return response;
    }

    @Override
    public void addRole(AddRoleRequest request) {
        if(!checkrole(request.getCurrentRole(),"ADD_ROLE")){
            throw new CustomExceptions.ValidationException("Khong co quyen them role");
        }
              Role newRole = new Role();
        newRole.setName(request.getRoleName());
        newRole.setDescription(request.getDescription());
        roleRepository.save(newRole);
        List<Permissions> permissions = request.getPermissions();
        for (Permissions p : permissions) {
            Role_permissions rp = new Role_permissions();
            rp.setRole(newRole);
            rp.setPermission(p);
            rp.setCreatedAt(Instant.now());
            rolePermissionsRepository.save(rp);
        }
    }

    @Override
    public void deleteRole(DeleteRoleRequest request) {
       if(checkRoleDelete(request.getRoleName(),request.getCurrentRole(),"DELETE_ROLE")){
              Role role = findRoleByName(request.getRoleName());
           roleRepository.delete(role);
       }


    }

    @Override
    public Role findRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new CustomExceptions.ValidationException("Role not found"));
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new CustomExceptions.ValidationException("Role not found"));
    }

    private boolean checkrole(String roleName,String permission) {
        RolePermissionResponse rolePermissionResponse= getPermissionsByRoleName(roleName);
        List<Permissions> permissions = rolePermissionResponse.getPermissions();
        for (Permissions p : permissions) {
            if (p.getName().equalsIgnoreCase(permission)) {
                return true;
            }
        }
        return "ADMIN".equalsIgnoreCase(roleName);

    }

    private boolean checkRoleDelete(String roleName,String currentRole,String permission) {
        Role roleDelete = findRoleByName(roleName);
        Role role = findRoleByName(currentRole);
        if(!role.getIsSystemRole()){
            return false;
        }
        if(roleDelete.getLevel() > role.getLevel()){
            return false;
        }

        if(checkrole(currentRole,permission)){
            return true;
        }

        return false;
    }
}