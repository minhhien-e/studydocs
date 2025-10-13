package com.domain.entity;

import com.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;

    private String fullName;
    private String username;
    private String email;
    private String phoneNumber;
    private String avatarUrl;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;

}

