package com.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Id
    private String id;
    private String fullName;
    private String username;
    private String email;
    private long phoneNumber;
    private String avatarUrl;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
}
