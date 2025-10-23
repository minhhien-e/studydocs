package com.example.authservicev2.domain.model.request;

import com.example.authservicev2.domain.model.entities.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RolePermissionRequest {
    private Role role;
    public String getRoleName() {
        return role != null ? role.getName() : null;
    }
}
