package com.domain.command;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "commandOf")
public class DeleteUser implements UserCommand {
    UUID userId;
}