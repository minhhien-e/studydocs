package com.example.authservicev2.util.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUserInfo {
    private String providerUserId;
    private String email;
    private String name;
    private String picture;
}
