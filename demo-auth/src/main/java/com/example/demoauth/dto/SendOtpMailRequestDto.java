package com.example.demoauth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendOtpMailRequestDto {
    private String userId;
    private String email;
    private long ttlSeconds;
}
