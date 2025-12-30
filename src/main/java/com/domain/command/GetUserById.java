package com.domain.command;

import lombok.Value;

import java.time.LocalDateTime;

import java.util.UUID;

@Value(staticConstructor = "commandOf")
public class GetUserById implements UserCommand {
    UUID userId;
    LocalDateTime timestamp = LocalDateTime.now();
}