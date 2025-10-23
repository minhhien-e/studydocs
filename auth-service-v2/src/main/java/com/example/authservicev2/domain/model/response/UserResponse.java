package com.example.authservicev2.domain.model.response;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
}
