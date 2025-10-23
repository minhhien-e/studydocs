package com.example.authservicev2.domain.model.response;

import com.example.authservicev2.domain.enums.Status;
import com.example.authservicev2.domain.model.entities.Role;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailUserResponse {
    private Long id;
    private String email;
    private String name;
    private String providerUserId;
    private String provider;
    private Status status;
    private List<Role> role;
}
