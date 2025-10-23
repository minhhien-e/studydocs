package com.example.authservicev2.domain.model.response;

import com.example.authservicev2.domain.model.entities.Permissions;
import com.example.authservicev2.domain.model.entities.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class RolePermissionResponse {
    private Role role;
    private List<Permissions> permissions;
    private List<String> permissionNames;
    private int totalPermissions;

    public RolePermissionResponse(Role role, List<Permissions> permissions) {
        this.role = role;
        this.permissions = permissions;
        this.permissionNames = permissions.stream()
                .map(Permissions::getName)
                .toList();
        this.totalPermissions = permissions.size();
    }
}