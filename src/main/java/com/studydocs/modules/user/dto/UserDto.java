package com.studydocs.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String email;
    private String fullName;
    private String username;
    private String avatarUrl;
    private String bio;
    private Long universityId;
    private String universityName;
    private Long facultyId;
    private String major;
    private Boolean isPrivate;
    private Integer followerCount;
    private Integer followingCount;
    private Integer documentCount;
}
