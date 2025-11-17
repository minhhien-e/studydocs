package com.domain.command;

import lombok.Value;

@Value(staticConstructor = "commandOf")
public class CheckUserExists implements UserCommand{
    String userId;
}
