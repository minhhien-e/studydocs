package com.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
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
