package com.example.demoauth.remote;

import com.example.demoauth.dto.SendOtpMailRequestDto;

public interface PublishNotificationEventPort {
    void publish(SendOtpMailRequestDto request);
}

