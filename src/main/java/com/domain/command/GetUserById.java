package com.domain.command;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "commandOf")
public class GetUserById implements UserCommand {
    String userId;
    LocalDateTime timestamp = LocalDateTime.now();
}