package com.infrastructure;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;


import java.util.UUID;

@Component
public  class JwtCurrentUserProvider {
    public UUID getCurrentUserId() {
        var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(jwt.getSubject());
//        return UUID.fromString("3fa85f64-5717-4562-b3fc-2c963f66afa6");
    }
}