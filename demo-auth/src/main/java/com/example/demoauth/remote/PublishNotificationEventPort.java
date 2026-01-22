package com.example.demoauth.remote;

import com.example.demoauth.remote.otp.dto.OtpSentPayload;

public interface PublishNotificationEventPort {
    void publishOtpSent(OtpSentPayload payload);
}

