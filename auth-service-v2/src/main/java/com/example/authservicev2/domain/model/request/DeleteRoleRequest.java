package com.example.authservicev2.domain.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteRoleRequest {
    private String roleName;
    private String currentRole;
}
