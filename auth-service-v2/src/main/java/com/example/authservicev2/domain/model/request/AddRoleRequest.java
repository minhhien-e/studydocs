package com.example.authservicev2.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.authservicev2.domain.model.entities.Permissions;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddRoleRequest {
    private String roleName;
    private String currentRole;
    private List<Permissions> permissions;
    private String description;
}
