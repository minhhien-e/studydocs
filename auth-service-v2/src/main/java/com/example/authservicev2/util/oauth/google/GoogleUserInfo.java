package com.example.authservicev2.util.oauth.google;

import lombok.Data;

@Data
public class GoogleUserInfo {
    private String sub;
    private String email;
    private String name;
    private String picture;
}