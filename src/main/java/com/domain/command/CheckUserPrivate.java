package com.domain.command;

import lombok.Value;
@Value(staticConstructor = "commandOf")
public class CheckUserPrivate implements UserCommand{
    String userId;
}

